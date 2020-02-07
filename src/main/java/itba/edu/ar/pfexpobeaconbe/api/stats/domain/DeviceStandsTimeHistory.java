package itba.edu.ar.pfexpobeaconbe.api.stats.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DeviceStandsTimeHistory {

    @Id @GeneratedValue
    private Long id;
    private String deviceId;
    private String standId;
    private double avgTime;

    public DeviceStandsTimeHistory() {
    }

    public DeviceStandsTimeHistory(final String deviceId, final String standId, final double avgTime) {
        this.deviceId = deviceId;
        this.standId = standId;
        this.avgTime = avgTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getStandId() {
        return standId;
    }

    public double getAvgTime() {
        return avgTime;
    }
}
