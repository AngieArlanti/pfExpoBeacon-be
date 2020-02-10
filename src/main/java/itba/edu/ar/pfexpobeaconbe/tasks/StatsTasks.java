package itba.edu.ar.pfexpobeaconbe.tasks;

import itba.edu.ar.pfexpobeaconbe.api.stats.application.DeviceLocationHistory;
import itba.edu.ar.pfexpobeaconbe.api.stats.application.DeviceLocationHistoryService;
import itba.edu.ar.pfexpobeaconbe.api.stats.application.DeviceStandsTimeHistoryService;
import itba.edu.ar.pfexpobeaconbe.api.stats.application.StandVisitHoursService;
import itba.edu.ar.pfexpobeaconbe.api.stats.domain.StandVisitHours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class StatsTasks {

    @Autowired
    private DeviceLocationHistoryService deviceLocationHistoryService;

    @Autowired
    private DeviceStandsTimeHistoryService deviceStandsTimeHistoryService;

    @Autowired
    private StandVisitHoursService standVisitHoursService;

    /**
     * This Task runs at 21hs every week day.
     */
    //@Scheduled(cron = "0 0 21 * * MON-FRI")
    //TODO erase whean feature is ended - every minute for dev
    @Scheduled(cron = "0/10 * * * * *")
    public void getAverageStandTimePerDeviceTask() {
        final Map<String, List<DeviceLocationHistory>> deviceLocationHistoryMap =
                deviceLocationHistoryService.getDeviceNextToStandRegisters();

        for (String deviceId : deviceLocationHistoryMap.keySet()) {
            final List<DeviceLocationHistory> list = deviceLocationHistoryMap.get(deviceId);
            deviceStandsTimeHistoryService.save(deviceId, list);
            deviceLocationHistoryService.markAverageTimeTaskProcessed(list);
        }

        deviceLocationHistoryService.updateStandWithAverageTime();
    }

    //@Scheduled(cron = "0 0 21 * * MON-FRI")
    //TODO erase whean feature is ended - every minute for dev
    @Scheduled(cron = "0/10 * * * * *")
    public void getHistogramTask(){
        final List<StandVisitHours> standVisitHours = deviceLocationHistoryService.findStandVisitHours();
        standVisitHoursService.saveAll(standVisitHours);
        markHistogramTaskProcessed();
    }

    private void markHistogramTaskProcessed() {
        List<DeviceLocationHistory> deviceLocationHistories
                = deviceLocationHistoryService.findByHistogramProcessedFalse();
        deviceLocationHistoryService.markHistogramTaskProcessed(deviceLocationHistories);
    }
}
