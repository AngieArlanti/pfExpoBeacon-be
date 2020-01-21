package api.stand.application;

import api.stand.domain.DeviceProximity;
import api.stand.domain.DeviceProximityRepository;
import api.stand.domain.Stand;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class DeviceProximityService {

    @Autowired
    private DeviceProximityRepository deviceProximityRepository;

    private StandService standService = new StandService();
    private DeviceProximityMapper mapper = new DeviceProximityMapper();

    public void addDeviceProximity(final DeviceProximityDto deviceProximityDto) {
        checkValidStand(deviceProximityDto.getImmediateStandId());
        DeviceProximity deviceProximity = mapper.toModel(deviceProximityDto);
        deviceProximity.setUpdateTime(OffsetDateTime.now());
        deviceProximityRepository.save(deviceProximity);
    }

    private void checkValidStand(String immediateStandId) {
        Stand found = standService.findBy(immediateStandId);
        Validate.notNull(found);
    }
}
