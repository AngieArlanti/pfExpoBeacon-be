package api.deviceproximity.application;

import api.deviceproximity.domain.DeviceProximity;
import api.deviceproximity.domain.DeviceProximityRepository;
import api.stand.application.StandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class DeviceProximityServiceTest {

    @TestConfiguration
    static class DeviceProximityServiceTestConfiguration {

        @Bean
        public DeviceProximityService deviceProximityService() {
            return new DeviceProximityService();
        }
    }

    @Autowired
    private DeviceProximityService deviceProximityService;

    @MockBean
    private DeviceProximityRepository deviceProximityRepository;

    @MockBean
    private StandService standService;

    @MockBean
    private TrilaterationService trilaterationService;

    @MockBean
    private LocationService locationService;

    @Test
    public void listAllImmediateStandRegisters() {
        List<DeviceProximity> mockDeviceProximities = new ArrayList<>();
        final DeviceProximity deviceProximity1 = new DeviceProximity("device_1", "",
                0.1, OffsetDateTime.now());
        final DeviceProximity deviceProximity2 = new DeviceProximity("device_1", "",
                0.8, OffsetDateTime.now());
        mockDeviceProximities.add(deviceProximity1);
        mockDeviceProximities.add(deviceProximity2);
        when(deviceProximityRepository.findByOrderByDistanceAscUpdateTimeDesc())
                .thenReturn(mockDeviceProximities);

        final List<DeviceProximity> deviceProximities = deviceProximityService.listAllImmediateStandRegisters();

        assertThat(deviceProximities, hasSize(1));
        assertThat(deviceProximities.get(0).getDistance(), is(deviceProximity1.getDistance()));
    }

}