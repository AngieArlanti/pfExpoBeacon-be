package api.stats.application;

import api.deviceproximity.application.DeviceProximityService;
import api.deviceproximity.domain.DeviceProximity;
import api.stand.application.StandService;
import api.stand.domain.Stand;
import api.stats.application.utils.StatsInterval;
import api.stats.domain.ExpoHours;
import api.stats.domain.ExpoHoursRepository;
import api.stats.domain.StandVisitHours;
import api.stats.domain.StandVisitHoursRepository;
import api.tour.domain.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static api.stats.application.utils.StatsUtils.*;
import static java.time.OffsetTime.now;
import static java.util.stream.Collectors.groupingBy;

/**
 * Service to get stats from DeviceProximity and DeviceProximityHistory Entities.
 */
@Service
public class StatsService {

    @Autowired
    private StandService standService;

    @Autowired
    private StandVisitHoursRepository standVisitHoursRepository;

    @Autowired
    private ExpoHoursRepository expoHoursRepository;

    /**
     * Device Proximity Service to ask for device immediate Stand's info.
     */
    @Autowired
    private DeviceProximityService deviceProximityService;

    private StandVisitHoursMapper standVisitHoursMapper = new StandVisitHoursMapper();

    /**
     * Returns amount of visits per hour for the given Stand Id.
     *
     * @param standId to calculate amount of visits per hour.
     * @return amount of visits per hour for the given Stand Id.
     */
    List<StandVisitHoursDto> getStandVisitHours(final String standId) {
        List<StandVisitHours> standVisitHours = standVisitHoursRepository.findByStandId(standId);
        List<ExpoHours> expoHours = expoHoursRepository.findAll();

        return standVisitHoursMapper.toDto(standVisitHours, expoHours);
    }

    /**
     * Returns amount of visits per hour for all Stands.
     *
     * @return amount of visits per hour for the all Stands.
     */
    List<StandVisitHoursDto> getAllStandVisitHours() {
        List<StandVisitHours> standVisitHours = standVisitHoursRepository.findAll();
        List<ExpoHours> expoHours = expoHoursRepository.findAll();

        return standVisitHoursMapper.toDto(standVisitHours, expoHours);
    }

    /**
     * Returns a map with Stand Ids as a key and a Long representing Stand's
     * current congestion, which is the amount of Stand's visits in the last ten minutes.
     *
     * @return Map with Stand Id as a key and a Long representing Stand's current congestion.
     */
    public Map<String, Long> getStandCurrentCongestion() {
        //Obtain Stand Proximity Info.
        List<DeviceProximity> deviceProximityList = deviceProximityService.listAll();

        //Calculate how many people was in each stand in the last 10 minutes.
        return deviceProximityList.stream().filter(this::isUpdated)
                .collect(groupingBy(DeviceProximity::getStandId, Collectors.counting()));
    }

    /**
     * Returns whether deviceProximity record is at most ten minutes old.
     *
     * @return true if deviceProximity is updated (it is 10 minutes old).
     */
    private boolean isUpdated(final DeviceProximity deviceProximity) {
        return Duration.between(now(), deviceProximity.getUpdateTime()).getSeconds() <= 600;
    }

    /**
     * Returns a map with Stand Ids as a key and a Long representing Stand's
     * current historic congestion, which is the historical average amount of Stand's visits in current
     * interval of ExpoHours.
     *
     * @return Map with Stand Id as a key and a Long representing Stand's current historic congestion.
     */
    public Map<String, Long> getStandCurrentHistoricCongestion() {
        final List<StandVisitHoursDto> standVisitHours = getAllStandVisitHours();

        return standVisitHours
                .stream()
                .filter(StandVisitHoursDto::matchesCurrentTime)
                .collect(Collectors.toMap(StandVisitHoursDto::getStandId, StandVisitHoursDto::getVisits));
    }

    public List<StandStatics> getCurrentStandStats(final List<Stand> stands) {
        final Map<String, Long> currentCongestion = getStandCurrentCongestion();
        final Map<String, Long> historicCongestion = getStandCurrentHistoricCongestion();

        return getStandStatics(stands, currentCongestion, historicCongestion);
    }

    List<StandStatics> getStandStatics(final List<Stand> stands, final Map<String, Long> currentCongestion,
                                       final Map<String, Long> historicCongestion) {
        return stands.stream()
                .map(stand -> new StandStatics(stand,
                        getNormalizedRanking(stand),
                        getNormalizedCurrentCongestion(currentCongestion, stand),
                        getNormalizedOpportunity(currentCongestion, historicCongestion, stand)))
                .collect(Collectors.toList());
    }

    public List<Tour> getPopularTours(final int amountLimit) {
        return Collections.emptyList();
    }

    private double getNormalizedOpportunity(final Map<String, Long> currentCongestion,
                                            final Map<String, Long> historicCongestion, final Stand stand) {
        return getNormalizedValue(getOpportunity(coalesce(currentCongestion.get(stand.getId())),
                coalesce(historicCongestion.get(stand.getId()))), 0L,
                coalesce(historicCongestion.get(stand.getId())));
    }

    private double getNormalizedCurrentCongestion(final Map<String, Long> currentCongestion, final Stand stand) {
        final StatsInterval statsInterval = getStatsInterval(currentCongestion.values());
        return getNormalizedValue(coalesce(currentCongestion.get(stand.getId())), statsInterval.getMin(), statsInterval.getMax());
    }

    private double getNormalizedRanking(final Stand stand) {
        return getNormalizedValue((long) stand.getRanking(), 1L, 5L);
    }

    private Long getOpportunity(final Long currentCongestion, final Long historicCongestion) {
        return historicCongestion - currentCongestion;
    }
}
