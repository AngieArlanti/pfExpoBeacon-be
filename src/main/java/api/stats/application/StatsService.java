package api.stats.application;

import api.deviceproximity.application.DeviceProximityService;
import api.deviceproximity.domain.DeviceProximity;
import api.position.domain.Position;
import api.stand.application.StandService;
import api.stand.domain.Stand;
import api.stats.application.utils.StatsDoubleInterval;
import api.stats.application.utils.StatsLongInterval;
import api.stats.application.utils.StatsUtils;
import api.stats.domain.*;
import api.tour.domain.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.*;
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

    @Autowired
    private TourVisitsRepository tourVisitsRepository;

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

    public List<StandStatics> getCurrentStandStats(final Position startPosition, final List<Stand> stands) {
        final Map<String, Long> currentCongestion = getStandCurrentCongestion();
        final Map<String, Long> historicCongestion = getStandCurrentHistoricCongestion();
        final Map<String, Double> linearDistancesToStartPoint = getLinearDistanceToStartPosition(startPosition, stands);

        return getStandStatics(linearDistancesToStartPoint, stands, currentCongestion, historicCongestion);
    }

    private Map<String, Double> getLinearDistanceToStartPosition(final Position startPosition,
                                                                 final List<Stand> stands) {
        final Map<String, Double> linearDistances = new HashMap<>();
        stands.forEach(stand -> linearDistances.put(stand.getId(),
                StatsUtils.getLinearDistance(startPosition, stand)));
        return linearDistances;
    }

    List<StandStatics> getStandStatics(final Map<String, Double> linearDistancesToStartPoint,
                                       final List<Stand> stands,
                                       final Map<String, Long> currentCongestion,
                                       final Map<String, Long> historicCongestion) {
        return stands.stream()
                .map(stand -> new StandStatics(stand,
                        getNormalizedRanking(stand),
                        getNormalizedCurrentCongestion(currentCongestion, stand),
                        getNormalizedOpportunity(currentCongestion, historicCongestion, stand),
                        getNormalizedDistanceToStartPosition(linearDistancesToStartPoint, stand)))
                .collect(Collectors.toList());
    }

    public List<Tour> getPopularTours(final int top) {
        final List<TourVisits> toursVisits = tourVisitsRepository.findByOrderByVisitsDesc();
        final List<Tour> popularTours = new ArrayList<>();

        for (final TourVisits tourVisits : toursVisits) {
            if (popularTours.size() <= top) {
                popularTours.add(getTour(tourVisits));
            }
        }
        return popularTours;
    }

    private Tour getTour(final TourVisits tourVisits) {
        List<String> tours = Arrays.asList(tourVisits.getTour().split(" "));
        final List<Stand> tour = standService.findBy(tours);

        return new Tour(tour, tourVisits.getVisits());
    }

    private double getNormalizedOpportunity(final Map<String, Long> currentCongestion,
                                            final Map<String, Long> historicCongestion, final Stand stand) {
        return getNormalizedValue(getOpportunity(coalesce(currentCongestion.get(stand.getId())),
                coalesce(historicCongestion.get(stand.getId()))), 0L,
                coalesce(historicCongestion.get(stand.getId())));
    }

    private double getNormalizedCurrentCongestion(final Map<String, Long> currentCongestion, final Stand stand) {
        final StatsLongInterval statsInterval = getStatsLongInterval(currentCongestion.values());
        return getNormalizedValue(coalesce(currentCongestion.get(stand.getId())), statsInterval.getMin(), statsInterval.getMax());
    }

    private double getNormalizedRanking(final Stand stand) {
        return getNormalizedValue((long) stand.getRanking(), 1L, 5L);
    }

    private Long getOpportunity(final Long currentCongestion, final Long historicCongestion) {
        return historicCongestion - currentCongestion;
    }

    private double getNormalizedDistanceToStartPosition(final Map<String, Double> linearDistancesToStartPoint,
                                                        final Stand stand) {
        final StatsDoubleInterval statsInterval = getStatsDoubleInterval(linearDistancesToStartPoint.values());
        final double distance = linearDistancesToStartPoint.get(stand.getId());
        return getNormalizedValue(distance, statsInterval.getMin(), statsInterval.getMax());
    }
}
