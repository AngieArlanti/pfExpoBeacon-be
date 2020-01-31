package api.tour.application;

import api.deviceproximity.application.DeviceProximityService;
import api.deviceproximity.domain.DeviceProximity;
import api.stand.application.StandService;
import api.stand.domain.Stand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.OffsetTime.now;
import static java.util.stream.Collectors.groupingBy;

@Service
public class TourService {

    /** Time people last to walk from stand to stand.
     * It is measured in minutes.
     * TODO this can be calculated async with history.
     */
    private static final Double TIME_BETWEEN_STANDS =  2.0;

    /** Device Proximity Service to ask for device immediate Stand's info.
     */
    @Autowired
    private DeviceProximityService deviceProximityService;

    /** Stand Service to ask for Stands specific information.
     */
    @Autowired
    private StandService standService;

    /** Returns a Stand's list filtered by ranking and Stand congestion.
     * It is ordered from less congested Stands to most congested ones.
     *
     * @return a Stand's list representing a tour throw best ranked and less congested Stands.
     */
    List<Stand> getTourWithoutLines() {
        //Obtain Stand Proximity Info.
        List<DeviceProximity> deviceProximityList = deviceProximityService.listAll();

        //Calculate how many people was in each stand in the last 10 minutes.
        Map<String, Long> congestionMap =  deviceProximityList.stream().filter(this::isUpdated)
                .collect(groupingBy(DeviceProximity::getStandId, Collectors.counting()));

        //Find Stands info.
        List<Stand> stands = standService.findBy(congestionMap.keySet().stream().collect(Collectors.toList()));

        //Filter stands by ranking. We want to recommend the best ranked ones.
        List<Stand> standsFilteredByRanking = stands.stream()
                .filter(stand -> stand.getRanking() >= 3).collect(Collectors.toList());

        //Build tour by ordering best ranked and least congested Stands.
        Map<Stand, Long> standCongestion = new HashMap<>();

        for (Stand stand: standsFilteredByRanking) {
            Long congestion = congestionMap.get(stand.getId());
            standCongestion.put(stand, congestion);
        }

        List<Stand> orderedSuggestedStandIds = standCongestion.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return orderedSuggestedStandIds;
    }

    /** Returns whether deviceProximity record is at most ten minutes old.
     *
     * @return true if deviceProximity is updated (it is 10 minutes old).
     */
    private boolean isUpdated(DeviceProximity deviceProximity) {
        return Duration.between(now(), deviceProximity.getUpdateTime()).getSeconds() <= 600;
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
