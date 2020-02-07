package itba.edu.ar.pfexpobeaconbe.api.deviceproximity.application;

import itba.edu.ar.pfexpobeaconbe.api.deviceproximity.domain.DeviceProximity;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

class DeviceProximityMapper {

    List<DeviceProximity> toModel(final DeviceProximityDto deviceProximityDto) {
        List<DeviceProximity> deviceProximityList = new ArrayList<>();

        for (NearbyStandDto nearbyStandDto: deviceProximityDto.getNearbyStands()) {
            deviceProximityList.add(new DeviceProximity(deviceProximityDto.getDeviceId(),
                    nearbyStandDto.getStandId(), nearbyStandDto.getDistance(), OffsetDateTime.now()));
        }
        return deviceProximityList;
    }
}
