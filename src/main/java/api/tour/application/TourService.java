package api.tour.application;

import api.position.domain.Position;
import api.stand.application.StandService;
import api.stand.domain.Stand;
import api.stats.application.StandStatics;
import api.stats.application.StatsService;
import api.tour.domain.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static api.stats.application.utils.StatsUtils.coalesce;

@Service
public class TourService {

    /**
     * Time people last to walk from stand to stand.
     * It is measured in minutes.
     * TODO this can be calculated async with history.
     */
    private static final Double TIME_BETWEEN_STANDS = 2.0;

    /**
     * Mapper to convert from Tour to TourDto.
     */
    private TourMapper tourMapper;

    /**
     * Stand Service to ask for Stands specific information.
     */
    @Autowired
    private StandService standService;

    @Autowired
    private StatsService statsService;

    private Position entrance;

    public TourService() {
        this.tourMapper = new TourMapper();
        //TODO (ma 2020-02-12) entrance Position is harcoded, must be brought from backoffice.
        this.entrance = new Position(0.33, 0.33);
    }

    /**
     * Returns a Tour which is a Stand's list ordered from best ranked and less congested stands
     * to worst ranked and more congested ones.
     * Also the unusual free stands at this very moment
     * are prioritized.
     *
     * @return a Tour ordered by current best ranked and less congested Stands.
     */
    TourDto getTourWithoutLines() {
        //Find Stands info.
        final List<Stand> stands = standService.findAll();
        return tourMapper.toDtoFromStands(sortStandsByTimeTourStats(entrance, stands));
    }

    /**
     * Returns a Tour which is a Stand's list ordered from best ranked and less congested stands
     * to worst ranked and more congested ones.
     * Also the unusual free stands at this very moment
     * are prioritized.
     *
     * @param currentStand  start point for tour.
     * @param pendingStands
     * @return a Tour ordered by current best ranked and less congested Stands.
     */
    TourDto getNextBestStandToVisitTour(final Stand currentStand, final List<Stand> pendingStands) {
        final Position lastStandPosition = new Position(currentStand.getLongitude(), currentStand.getLatitude());
        return tourMapper.toDtoFromStands(sortStandsByTimeTourStats(lastStandPosition, pendingStands));
    }

    /**
     * Returns a tour without lines limited by the given timeLimit.
     *
     * @param timeLimit total time a person has to spend in this tour.
     *                  It is measured in hours.
     * @return a tour without lines limited by the given timeLimit.
     */
    TourDto getTimeLimitedTour(final Double timeLimit) {
        final TourDto tourWithoutLines = getTourWithoutLines();
        final List<Stand> timeLimitedTour = new ArrayList<>();
        Double totalTime = 0.0;
        for (Stand stand : tourWithoutLines.getTour()) {
            totalTime += (coalesce(stand.getAverageTime()) / 60.0);
            totalTime += (TIME_BETWEEN_STANDS / 60.0);
            if (totalTime <= timeLimit) {
                timeLimitedTour.add(stand);
            }
        }
        return tourMapper.toDtoFromStands(timeLimitedTour);
    }

    /**
     * Returns Top Three Popular Paths visited by people in history.
     * Each Tour is ordered by current best ranked and less congested Stands.
     *
     * @return Top Three Tours ordered by current best ranked and less congested Stands.
     */
    List<TourDto> getTopThreeTours() {
        final List<Tour> tours = statsService.getPopularTours(3);
        final List<Tour> popularTours = new ArrayList<>();

        for (final Tour tour : tours) {
            popularTours.add(
                    new Tour(sortStandsByTopThreeTourStats(entrance, tour.getTour()),
                             tour.getVisits()));
        }

        //TODO now order by distance.

        return tourMapper.toDto(popularTours);
    }

    /**
     * Returns the given Stand's list sorted by Ranking, Current Congestion, and Opportunity
     * (which is the difference between historic and current congestion).
     * First the best ranked and less congested Stands taking into account the opportunity of
     * visiting an unusual free stand at this moment.
     *
     * @param pendingStands the Stands to sort.
     * @return given Stand's list sorted by Ranking, Current Congestion, and Opportunity.
     */
    List<Stand> sortStandsByTimeTourStats(final Position startPosition, final List<Stand> pendingStands) {
        final List<StandStatics> standStatics = statsService.getTimeTourStats(startPosition, pendingStands);
        return sortStandsByCurrentStats(standStatics);
    }

    /**
     * Returns the given Stand's list sorted by Ranking, Current Congestion, and Opportunity
     * (which is the difference between historic and current congestion).
     * First the best ranked and less congested Stands taking into account the opportunity of
     * visiting an unusual free stand at this moment.
     *
     * @param pendingStands the Stands to sort.
     * @return given Stand's list sorted by Ranking, Current Congestion, and Opportunity.
     */
    List<Stand> sortStandsByTopThreeTourStats(final Position startPosition, final List<Stand> pendingStands) {
        final List<StandStatics> standStatics = statsService.getPopularTourStats(startPosition, pendingStands);
        return sortStandsByCurrentStats(standStatics);
    }

    private List<Stand> sortStandsByCurrentStats(final List<StandStatics> standStatics) {
        standStatics.sort(Comparator.comparingDouble(StandStatics::getOrderCriteria).reversed());
        return standStatics.stream().map(StandStatics::getStand).collect(Collectors.toList());
    }
}
