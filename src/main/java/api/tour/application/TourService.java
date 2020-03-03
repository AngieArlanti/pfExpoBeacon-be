package api.tour.application;

import api.position.domain.Position;
import api.stand.application.StandService;
import api.stand.domain.Stand;
import api.stats.application.StandStatics;
import api.stats.application.StatsService;
import api.stats.application.utils.StatsUtils;
import api.tour.domain.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourService {

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
        this.entrance = new Position(-58.489280, -34.547081);
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
     * Returns Top Three Popular Paths visited by people in history.
     * Each Tour is ordered by current best ranked and less congested Stands.
     *
     * @return Top Three Tours ordered by current best ranked and less congested Stands.
     */
    List<TourDto> getTopThreeTours() {
        final List<Tour> tours = statsService.getPopularTours(3);

        final List<Tour> bestStandsToStart = getBestPopularStandToStartTours(tours);
        final List<Tour> topThreeTours = new ArrayList<>();

        for (final Tour tour : bestStandsToStart) {
            topThreeTours.add(new Tour(sortStandsByDistances(tour.getTour().get(0), tour.getTour()),
                    tour.getVisits()));
        }

        return tourMapper.toDto(topThreeTours);
    }

    /**
     * Returns best stands to start by current congestion, historic congestion and distance from entrance.
     *
     * @param tours to sort and find best stands to start.
     * @return best stands to start by current congestion, historic congestion and distance from entrance.
     */
    private List<Tour> getBestPopularStandToStartTours(List<Tour> tours) {
        final List<Tour> popularTours = new ArrayList<>();
        for (final Tour tour : tours) {
            popularTours.add(
                    new Tour(sortStandsByTopThreeTourStats(entrance, tour.getTour()),
                             tour.getVisits()));
        }
        return popularTours;
    }

    /**
     * Returns the given Stand's list sorted by Ranking, Current Congestion, and Opportunity
     * (which is the difference between historic and current congestion).
     * First the best ranked and less congested Stands taking into account the opportunity of
     * visiting an unusual free stand at this moment.
     *
     * @param startPosition the starting Position to calculate distance between stands and it.
     * @param pendingStands the Stands to sort.
     * @return given Stand's list sorted by Ranking, Location, Current Congestion, and Opportunity.
     */
    List<Stand> sortStandsByTimeTourStats(final Position startPosition, final List<Stand> pendingStands) {
        final List<StandStatics> standStatics = statsService.getTimeTourStats(startPosition, pendingStands);
        return sortStandsByCurrentStats(standStatics);
    }

    /**
     * Returns the given Stand's list sorted by Location, Current Congestion, and Opportunity
     * (which is the difference between historic and current congestion).
     *
     * @param startPosition the starting Position to calculate distance between stands and it.
     * @param pendingStands the Stands to sort.
     * @return given Stand's list sorted by Location, Current Congestion, and Opportunity.
     */
    List<Stand> sortStandsByTopThreeTourStats(final Position startPosition, final List<Stand> pendingStands) {
        final List<StandStatics> standStatics = statsService.getPopularTourStats(startPosition, pendingStands);
        return sortStandsByCurrentStats(standStatics);
    }

    private List<Stand> sortStandsByCurrentStats(final List<StandStatics> standStatics) {
        standStatics.sort(Comparator.comparingDouble(StandStatics::getOrderCriteria).reversed());
        return standStatics.stream().map(StandStatics::getStand).collect(Collectors.toList());
    }

    /**
     * Returns a Stands' list ordered by distance.
     *
     * @param startStand the starting stand to calculate distance to it.
     * @param stands to order.
     * @return
     */
    //TODO (ma 2020-02-13) Improve complexity.
    List<Stand> sortStandsByDistances(final Stand startStand, final List<Stand> stands) {
        final List<Stand> minPath = new ArrayList<>();
        Stand currentStand = startStand;
        while (!stands.isEmpty()) {
            Stand closestStand = getClosestStand(currentStand, stands);
            stands.remove(closestStand);
            minPath.add(closestStand);
            currentStand = closestStand;
        }
        return minPath;
    }

    /**
     * Returns closest stand to given currentStand.
     *
     * @param currentStand reference stand to calculate distances from.
     * @param stands list to calculate closest from currentStand.
     *
     * @return closest stand from stands to given currentStand.
     */
    private Stand getClosestStand(final Stand currentStand,
                                  final List<Stand> stands){
        final Position startPosition = new Position(currentStand.getLatitude(), currentStand.getLongitude());
        return stands.stream()
                .min(Comparator.comparingDouble(value -> StatsUtils.getHaversineDistance(startPosition, value)))
                .orElse(null);
    }
}
