package itba.edu.ar.pfexpobeaconbe.api.stats.application;

import itba.edu.ar.pfexpobeaconbe.api.stand.application.StandService;
import itba.edu.ar.pfexpobeaconbe.api.stats.domain.DeviceLocationHistoryRepository;
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
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DeviceLocationHistoryServiceTest {

    @TestConfiguration
    static class deviceLocationHistoryServiceTestConfiguration {
        @Bean
        public DeviceLocationHistoryService deviceLocationHistoryService() {
            return new DeviceLocationHistoryService();
        }
    }

    @MockBean
    private DeviceLocationHistoryRepository deviceLocationHistoryRepository;

    @Autowired
    private DeviceLocationHistoryService deviceLocationHistoryService;

    @MockBean
    private StandService standService;

    @MockBean
    private DeviceStandsTimeHistoryService deviceStandsTimeHistoryService;

    @Test
    public void getDeviceNextToStandRegisters_deviceIdsVisitDifferentStands() {
        final DeviceLocationHistory register1 = new DeviceLocationHistory("device_1", "stand_1",
                0.5, OffsetDateTime.parse("2020-02-06T23:07:36Z", DateTimeFormatter.ISO_ZONED_DATE_TIME),
                false, false , 1L);
        final DeviceLocationHistory register2 = new DeviceLocationHistory("device_1", "stand_1",
                0.5, OffsetDateTime.parse("2020-02-06T23:09:36Z", DateTimeFormatter.ISO_ZONED_DATE_TIME),
                false, false , 1L);
        final DeviceLocationHistory register3 = new DeviceLocationHistory("device_1", "stand_2",
                0.5, OffsetDateTime.parse("2020-02-06T23:11:36Z", DateTimeFormatter.ISO_ZONED_DATE_TIME),
                false, false , 1L);
        final DeviceLocationHistory register4 = new DeviceLocationHistory("device_1", "stand_2",
                0.5, OffsetDateTime.parse("2020-02-06T23:12:36Z", DateTimeFormatter.ISO_ZONED_DATE_TIME),
                false, false , 1L);
        final DeviceLocationHistory register5 = new DeviceLocationHistory("device_1", "stand_2",
                1.5, OffsetDateTime.parse("2020-02-06T23:13:36Z", DateTimeFormatter.ISO_ZONED_DATE_TIME),
                false, false , 1L);
        final DeviceLocationHistory register6 = new DeviceLocationHistory("device_2", "stand_2",
                0.5, OffsetDateTime.parse("2020-02-06T23:12:36Z", DateTimeFormatter.ISO_ZONED_DATE_TIME),
                false, false , 1L);
        final DeviceLocationHistory register7 = new DeviceLocationHistory("device_2", "stand_2",
                0.5, OffsetDateTime.parse("2020-02-06T23:13:36Z", DateTimeFormatter.ISO_ZONED_DATE_TIME),
                false, false , 1L);
        List<DeviceLocationHistory> deviceLocationHistory = new ArrayList<>();
        deviceLocationHistory.add(register1);
        deviceLocationHistory.add(register2);
        deviceLocationHistory.add(register3);
        deviceLocationHistory.add(register4);
        deviceLocationHistory.add(register5);
        deviceLocationHistory.add(register6);
        deviceLocationHistory.add(register7);

        when(deviceLocationHistoryRepository.findByAverageTimeProcessedFalse()).thenReturn(deviceLocationHistory);

        Map<String, List<DeviceLocationHistory>> registers = deviceLocationHistoryService.getDeviceNextToStandRegisters();

        assertThat(registers.size(), is(2));
        assertThat(registers.get("device_1").size(), is(4));
        assertThat(registers.get("device_2").size(), is(2));
    }


}