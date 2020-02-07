package itba.edu.ar.pfexpobeaconbe.api.deviceproximity.domain;

import java.io.Serializable;
import java.util.Objects;

public class DeviceProximityId implements Serializable {

    private String deviceId;
    private String standId;

    public DeviceProximityId() {}

    public DeviceProximityId(String deviceId, String standId) {
        this.deviceId = deviceId;
        this.standId = standId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getStandId() {
        return standId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceProximityId that = (DeviceProximityId) o;
        return Objects.equals(deviceId, that.deviceId) &&
                Objects.equals(standId, that.standId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, standId);
    }
}
