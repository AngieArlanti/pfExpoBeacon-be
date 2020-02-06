package api.tour.application;

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
     * Stand Service to ask for Stands specific information.
     */
    @Autowired
    private StandService standService;

    @Autowired
    private StatsService statsService;

    /**
     * Returns a Stand's list ordered from best ranked and less congested stands
     * to worst ranked and more congested ones. Also the unusual free stands at this very moment
     * are prioritized.
     *
     * @return a Stand's list representing a tour throw best ranked and less congested Stands.
     */
    List<Stand> getTourWithoutLines() {
        //Find Stands info.
        final List<Stand> stands = standService.findAll();
        return sortStandsByCurrentStats(stands);
    }

    /**
     * Returns time limited tour by the given timeLimit.
     *
     * @param timeLimit total time a person has to spend in this tour.
     *                  It is measured in hours.
     * @return a Stand's list representing time limited tour.
     */
    List<Stand> getTimeLimitedTour(final Double timeLimit) {
        final List<Stand> tourWithoutLines = getTourWithoutLines();
        final List<Stand> timeLimitedTour = new ArrayList<>();
        Double totalTime = 0.0;
        for (Stand stand : tourWithoutLines) {
            totalTime += (coalesce(stand.getAverageTime()) / 60.0);
            totalTime += (TIME_BETWEEN_STANDS / 60.0);
            if (totalTime <= timeLimit) {
                timeLimitedTour.add(stand);
            }
        }
        return timeLimitedTour;
    }

    /**
     *
     * @return
     */
    List<Tour> getTopThreeTours() {
        final List<Tour> tours = statsService.getPopularTours(3);
        final List<Tour> popularTours = new ArrayList<>();

        for (final Tour tour : tours) {
            popularTours.add(
                    new Tour(sortStandsByCurrentStats(tour.getTour())
                            , tour.getVisits()));
        }

        return popularTours;
    }

    /**
     * Returns the given Stand's list sorted by Ranking, Current Congestion, and Opportunity
     * (which is the difference between historic and current congestion).
     * First the best ranked and less congested Stands taking into account the opportunity of
     * visiting an unusual free stand at this moment.
     *
     * @param stands the Stands to sort.
     * @return given Stand's list sorted by Ranking, Current Congestion, and Opportunity.
     */
    List<Stand> sortStandsByCurrentStats(final List<Stand> stands) {
        final List<StandStatics> standStatics = statsService.getCurrentStandStats(stands);
        standStatics.sort(Comparator.comparingDouble(StandStatics::getOrderCriteria).reversed());
        return standStatics.stream().map(StandStatics::getStand).collect(Collectors.toList());
    }
}
