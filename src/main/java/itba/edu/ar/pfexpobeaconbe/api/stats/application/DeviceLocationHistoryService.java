package itba.edu.ar.pfexpobeaconbe.api.stats.application;

import itba.edu.ar.pfexpobeaconbe.api.stand.application.StandService;
import itba.edu.ar.pfexpobeaconbe.api.stats.domain.DeviceLocationHistoryRepository;
import itba.edu.ar.pfexpobeaconbe.api.stats.domain.DeviceStandsTimeHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DeviceLocationHistoryService {

    private static final double IMMEDIATE_STAND_DISTANCE = 1.0;

    @Autowired
    private DeviceLocationHistoryRepository deviceLocationHistoryRepository;

    @Autowired
    private DeviceStandsTimeHistoryService deviceStandsTimeHistoryService;

    @Autowired
    private StandService standService;

    public Map<String, List<DeviceLocationHistory>> getDeviceNextToStandRegisters() {
        final List<DeviceLocationHistory> deviceLocationHistory =
                deviceLocationHistoryRepository.findByAverageTimeProcessedFalse();

        return deviceLocationHistory.stream().filter(deviceLocationHistory1
                -> deviceLocationHistory1.getDistance() < IMMEDIATE_STAND_DISTANCE)
              .collect(groupingBy(DeviceLocationHistory::getDeviceId, toList()));
    }

    public void updateAll(final List<DeviceLocationHistory> deviceLocationHistories) {
        deviceLocationHistories.forEach(DeviceLocationHistory::processed);
        deviceLocationHistoryRepository.saveAll(deviceLocationHistories);
    }

    public void updateStandWithAverageTime() {
        final List<DeviceStandsTimeHistory> deviceStandsTimeHistoryList
                = deviceStandsTimeHistoryService.findAll();

        Map<String, Double> standAverage = deviceStandsTimeHistoryList.stream()
                .collect(Collectors.groupingBy(DeviceStandsTimeHistory::getStandId))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, x -> calculateAverage(x.getValue())));

        standService.updateAverage(standAverage);
    }

    private double calculateAverage(final List<DeviceStandsTimeHistory> deviceStandsTimeHistoryByStand) {
        return deviceStandsTimeHistoryByStand
                .stream()
                .mapToDouble(DeviceStandsTimeHistory::getAvgTime)
                .average().getAsDouble();
    }
}
