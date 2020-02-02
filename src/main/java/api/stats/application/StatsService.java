package api.stats.application;

import api.deviceproximity.application.DeviceProximityService;
import api.deviceproximity.domain.DeviceProximity;
import api.stand.domain.Stand;
import api.stand.domain.StandRepository;
import api.stats.domain.ExpoHours;
import api.stats.domain.ExpoHoursRepository;
import api.stats.domain.StandVisitHours;
import api.stats.domain.StandVisitHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.OffsetTime.now;
import static java.util.stream.Collectors.groupingBy;

/**
 * Service to get stats from DeviceProximity and DeviceProximityHistory Entities.
 */
@Service
public class StatsService {

    @Autowired
    private StandRepository standRepository;

    @Autowired
    private StandVisitHoursRepository standVisitHoursRepository;

    @Autowired
    private ExpoHoursRepository expoHoursRepository;

    /** Device Proximity Service to ask for device immediate Stand's info.
     */
    @Autowired
    private DeviceProximityService deviceProximityService;

    private StandVisitHoursMapper standVisitHoursMapper = new StandVisitHoursMapper();

    /** Returns amount of visits per hour for the given Stand Id.
     *
     * @param standId to calculate amount of visits per hour.
     * @return amount of visits per hour for the given Stand Id.
     */
    List<StandVisitHoursDto> getStandVisitHours(final String standId) {
        List<StandVisitHours> standVisitHours = standVisitHoursRepository.findByStandId(standId);
        List<ExpoHours> expoHours = expoHoursRepository.findAll();

        return standVisitHoursMapper.toDto(standVisitHours, expoHours);
    }

    /** Returns amount of visits per hour for all Stands.
     *
     * @return amount of visits per hour for the all Stands.
     */
    List<StandVisitHoursDto> getAllStandVisitHours() {
        List<StandVisitHours> standVisitHours = standVisitHoursRepository.findAll();
        List<ExpoHours> expoHours = expoHoursRepository.findAll();

        return standVisitHoursMapper.toDto(standVisitHours, expoHours);
    }

    /** Returns a map with Stand Ids as a key and a Long representing Stand's
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

    /** Returns whether deviceProximity record is at most ten minutes old.
     *
     * @return true if deviceProximity is updated (it is 10 minutes old).
     */
    private boolean isUpdated(final DeviceProximity deviceProximity) {
        return Duration.between(now(), deviceProximity.getUpdateTime()).getSeconds() <= 600;
    }

    /** Returns a map with Stand Ids as a key and a Long representing Stand's
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

    public List<StandStatics> getCurrentStandStatics() {
        //TODO (ma 2020-02-02) Call ranking service when merging with Ranking Feature.
        List<Stand> stands = standRepository.findOrderedByRanking();
        //TODO (ma 2020-02-02) Improve given standIds as argument.
        final Map<String, Long> currentCongestion = getStandCurrentCongestion();
        final Map<String, Long> historicCongestion = getStandCurrentHistoricCongestion();

        final List<Long> minMaxCurrentCongestion = getMinMax(currentCongestion.values());
        final List<Long> minMaxHistoricCongestion = getMinMax(currentCongestion.values());

        return stands.stream()
                .map(stand -> new StandStatics(stand,
                        getNormalizedValue((long) stand.getRanking(),1L, 5L),
                        getNormalizedValue(coalesce(currentCongestion.get(stand.getId())), minMaxCurrentCongestion.get(0),  minMaxCurrentCongestion.get(1)),
                        getNormalizedOpportunity(coalesce(currentCongestion.get(stand.getId())),
                                coalesce(historicCongestion.get(stand.getId())), minMaxHistoricCongestion.get(0), minMaxHistoricCongestion.get(1))))
                .collect(Collectors.toList());
    }

    private List<Long> getMinMax(final Collection<Long> values) {
        final List<Long> result = new ArrayList<>();
        values.stream().min(Long::compareTo).ifPresent(aLong -> result.add(coalesce(aLong)));
        values.stream().max(Long::compareTo).ifPresent(aLong -> result.add(coalesce(aLong)));
        return result;
    }

    private double getNormalizedValue(final Long value, final Long min, final Long max){
        return (-1) * ((double)value / (max - min));
    }
    private Long getOpportunity(final Long currentCongestion, final Long historicCongestion) {
        return historicCongestion - currentCongestion;
    }

    private double getNormalizedOpportunity(final Long currentCongestion, final Long historicCongestion,
                                            final Long min, final Long max){
        return getNormalizedValue(getOpportunity(currentCongestion, historicCongestion), min, max);
    }

    private long coalesce(final Long value){
        if(value == null){
            return 0;
        }
        return value;
    }
}
