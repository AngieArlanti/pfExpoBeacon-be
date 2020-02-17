package itba.edu.ar.pfexpobeaconbe.api.stats.application;

import java.util.Set;

public class DeviceTours {

    private String deviceId;
    private Set<String> tour;

    public DeviceTours(final String deviceId, final Set<String> tour) {
        this.deviceId = deviceId;
        this.tour = tour;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public Set<String> getTour() {
        return tour;
    }
}
