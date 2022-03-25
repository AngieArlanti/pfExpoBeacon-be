package api.deviceproximity.application;

import api.deviceproximity.domain.DeviceProximity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

class DeviceProximityMapper {

    List<DeviceProximity> toModel(final DeviceProximityDto deviceProximityDto) {
        List<DeviceProximity> deviceProximityList = new ArrayList<>();
        for (NearbyStandDto nearbyStandDto: deviceProximityDto.getNearbyStands()) {
            TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
            deviceProximityList.add(new DeviceProximity(deviceProximityDto.getDeviceId(),
                    nearbyStandDto.getStandId(), nearbyStandDto.getDistance(), OffsetDateTime.now()));
        }
        return deviceProximityList;
    }
}
