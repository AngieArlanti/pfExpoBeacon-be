package api.deviceproximity.application;

import java.util.ArrayList;
import java.util.List;

public class DeviceProximityDto {

    private String deviceId;

    private List<NearbyStandDto> nearbyStands;

    public DeviceProximityDto() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public List<NearbyStandDto> getNearbyStands() {
        return nearbyStands;
    }

    public List<String> getNearbyStandIds() {
        List<String> nearbyStandIds = new ArrayList<>();
        for (NearbyStandDto ns: nearbyStands) {
            nearbyStandIds.add(ns.getStandId());
        }
        return nearbyStandIds;
    }
}
