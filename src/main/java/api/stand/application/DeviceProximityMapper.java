package api.stand.application;

import api.stand.domain.DeviceProximity;

public class DeviceProximityMapper {

    DeviceProximity toModel(final DeviceProximityDto deviceProximityDto){
        return new DeviceProximity(deviceProximityDto.getDeviceId(),
                deviceProximityDto.getImmediateStandId());
    }
}
