package itba.edu.ar.pfexpobeaconbe.api.stats.application;

import itba.edu.ar.pfexpobeaconbe.api.stats.domain.DeviceStandsTimeHistoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class DeviceStandsTimeHistoryServiceTest {

    @Autowired
    DeviceStandsTimeHistoryService deviceStandsTimeHistoryService;

    @MockBean
    DeviceStandsTimeHistoryRepository deviceStandsTimeHistoryRepository;

    @TestConfiguration
    static class DeviceStandsTimeHistoryServiceTestConfiguration{
        @Bean
        public DeviceStandsTimeHistoryService deviceStandsTimeHistoryService(){
            return new DeviceStandsTimeHistoryService();
        }
    }

    @Test
    public void save_oneDeviceStandsTimeHistory() {
        final DeviceLocationHistory register1 = new DeviceLocationHistory("device_1", "stand_1",
                0.5, OffsetDateTime.parse("2020-02-06T23:07:36Z", DateTimeFormatter.ISO_ZONED_DATE_TIME),
                false, false, 1L);
        final DeviceLocationHistory register2 = new DeviceLocationHistory("device_1", "stand_1",
                0.5, OffsetDateTime.parse("2020-02-06T23:09:36Z", DateTimeFormatter.ISO_ZONED_DATE_TIME),
                false, false, 1L);
        final DeviceLocationHistory register3 = new DeviceLocationHistory("device_1", "stand_2",
                0.5, OffsetDateTime.parse("2020-02-06T23:11:36Z", DateTimeFormatter.ISO_ZONED_DATE_TIME),
                false, false, 1L);
        final DeviceLocationHistory register4 = new DeviceLocationHistory("device_1", "stand_2",
                0.5, OffsetDateTime.parse("2020-02-06T23:12:36Z", DateTimeFormatter.ISO_ZONED_DATE_TIME),
                false, false, 1L);

        List<DeviceLocationHistory> deviceLocationHistory = new ArrayList<>();
        deviceLocationHistory.add(register1);
        deviceLocationHistory.add(register2);
        deviceLocationHistory.add(register3);
        deviceLocationHistory.add(register4);

        deviceStandsTimeHistoryService.save("device_1", deviceLocationHistory);

    }

}