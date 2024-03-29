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
import api.tour.application.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static api.stats.application.utils.StatsUtils.*;
import static java.util.stream.Collectors.*;

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
    public Map<String, Long> getStandCurrentCongestion(final List<Stand> stands) {
        //Obtain Stand Proximity Info.
        List<DeviceProximity> deviceProximityList =
          deviceProximityService.listAllImmediateStandRegisters();

        final Map<String, Long> congestion = new HashMap<>();
        stands.forEach(stand -> congestion.put(stand.getId(), 0L));

        //Calculate how many people was in each stand in the last 10 minutes.
        congestion.putAll(deviceProximityList.stream().filter(this::isUpdated)
                .collect(groupingBy(DeviceProximity::getStandId, Collectors.counting())));

        return congestion;
    }

    /**
     * Returns whether deviceProximity record is at most ten minutes old.
     * TODO ADJUST 1200sec WITH STAND AVG TIME.
     * @return true if deviceProximity is updated (it is 20 minutes old).
     */
    private boolean isUpdated(final DeviceProximity deviceProximity) {
        return Duration.between(deviceProximity.getUpdateTime(), OffsetDateTime.now()).getSeconds() <= 1200;
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

    public List<StandStatics> getTimeTourStats(final Position startPosition, final List<Stand> stands) {
        final Map<String, Long> currentCongestion = getStandCurrentCongestion(stands);
        final Map<String, Long> historicCongestion = getStandCurrentHistoricCongestion();
        final Map<String, Double> linearDistancesToStartPoint = getLinearDistanceToStartPosition(startPosition, stands);

        return getTimeTourStats(linearDistancesToStartPoint, stands, currentCongestion, historicCongestion);
    }

    public List<StandStatics> getPopularTourStats(final Position startPosition, final List<Stand> stands) {
        final Map<String, Long> currentCongestion = getStandCurrentCongestion(stands);
        final Map<String, Long> historicCongestion = getStandCurrentHistoricCongestion();
        final Map<String, Double> linearDistancesToStartPoint = getLinearDistanceToStartPosition(startPosition, stands);

        return getPopularTourStats(linearDistancesToStartPoint, stands, currentCongestion, historicCongestion);
    }

    public Map<String, Double> getLinearDistanceToStartPosition(final Position startPosition,
                                                                 final List<Stand> stands) {
        final Map<String, Double> linearDistances = new HashMap<>();
        stands.forEach(stand -> linearDistances.put(stand.getId(),
                StatsUtils.getHaversineDistance(startPosition, stand)));
        return linearDistances;
    }

    List<StandStatics> getTimeTourStats(final Map<String, Double> linearDistancesToStartPoint,
                                        final List<Stand> stands,
                                        final Map<String, Long> currentCongestion,
                                        final Map<String, Long> historicCongestion) {
        return stands.stream()
                .map(stand -> new TimeTourStandStatics(stand,
                        getNormalizedRanking(stand),
                        getNormalizedCurrentCongestion(currentCongestion, stand),
                        getNormalizedOpportunity(currentCongestion, historicCongestion, stand),
                        getNormalizedDistanceToStartPosition(linearDistancesToStartPoint, stand)))
                .collect(Collectors.toList());
    }

    List<StandStatics> getPopularTourStats(final Map<String, Double> linearDistancesToStartPoint,
                                        final List<Stand> stands,
                                        final Map<String, Long> currentCongestion,
                                        final Map<String, Long> historicCongestion) {
        return stands.stream()
                .map(stand -> new PopularTourStandStatics(stand,
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
        final List<String> tours = Arrays.asList(tourVisits.getStandIds().split(
          " "));
        final List<Stand> tour = standService.findBy(tours);

        return new Tour(tour, tourVisits.getVisits());
    }

    private double getNormalizedOpportunity(final Map<String, Long> currentCongestion,
                                            final Map<String, Long> historicCongestion, final Stand stand) {
        final Long opportunity = getOpportunity(coalesce(currentCongestion.get(stand.getId())),
                coalesce(historicCongestion.get(stand.getId())));
        final Long max = Math.abs(coalesce(historicCongestion.get(stand.getId()))) > Math.abs(opportunity) ?
                coalesce(historicCongestion.get(stand.getId())) : opportunity;
        return getNormalizedValue(opportunity, 0L, max);
    }

    private double getNormalizedCurrentCongestion(final Map<String, Long> currentCongestion, final Stand stand) {
        if(!currentCongestion.isEmpty()){
            final StatsLongInterval statsInterval = getStatsLongInterval(currentCongestion.values());
            return getNormalizedValue(coalesce(currentCongestion.get(stand.getId())), statsInterval.getMin(), statsInterval.getMax());
        }
        return 0.0;
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
