package api.tour.application;

import api.stand.application.StandService;
import api.stand.domain.Stand;
import api.stats.application.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TourService {

    /** Time people last to walk from stand to stand.
     * It is measured in minutes.
     * TODO this can be calculated async with history.
     */
    private static final Double TIME_BETWEEN_STANDS =  2.0;

    /** Stand Service to ask for Stands specific information.
     */
    @Autowired
    private StandService standService;

    @Autowired
    private StatsService statsService;

    /** Returns a Stand's list filtered by ranking and Stand congestion.
     * It is ordered from less congested Stands to most congested ones.
     *
     * @return a Stand's list representing a tour throw best ranked and less congested Stands.
     */
    List<Stand> getTourWithoutLines() {
        final Map<String, Long> currentCongestionMap = statsService.getStandCurrentCongestion();

        //Find Stands info.
        final List<Stand> stands = standService.findBy(new ArrayList<>(currentCongestionMap.keySet()));

        //Filter stands by ranking. We want to recommend the best ranked ones.
        final List<Stand> standsFilteredByRanking = stands.stream()
                .filter(stand -> stand.getRanking() >= 3).collect(Collectors.toList());

        //Build tour by ordering best ranked and least congested Stands.
        final Map<Stand, Long> standCongestion = new HashMap<>();

        for (Stand stand: standsFilteredByRanking) {
            Long congestion = currentCongestionMap.get(stand.getId());
            standCongestion.put(stand, congestion);
        }

        return standCongestion.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /** Returns time limited tour by the given timeLimit.
     *
     * @param timeLimit total time a person has to spend in this tour.
     *                  It is measured in hours.
     * @return a Stand's list representing time limited tour.
     */
    List<Stand> getTimeLimitedTour(final Double timeLimit) {
        final List<Stand> tourWithoutLines = getTourWithoutLines();
        final List<Stand> timeLimitedTour = new ArrayList<>();
        Double totalTime = 0.0;
        for (Stand stand: tourWithoutLines) {
            totalTime += (stand.getAverageTime()/60.0);
            totalTime += (TIME_BETWEEN_STANDS/60.0);
            if(totalTime <= timeLimit){
                timeLimitedTour.add(stand);
            }
        }
        return timeLimitedTour;
    }
}
