package itba.edu.ar.pfexpobeaconbe.api.stats.application;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class DeviceLocationHistory {

    @Id
    @GeneratedValue
    private Long id;
    private String deviceId;
    private String standId;
    private double distance;
    private OffsetDateTime updateTime;
    private Boolean averageTimeProcessed;

    public DeviceLocationHistory() {
    }

    public DeviceLocationHistory(final String deviceId, final String standId,
                                 final double distance, final OffsetDateTime updateTime,
                                 final Boolean averageTimeProcessed) {
        this.deviceId = deviceId;
        this.standId = standId;
        this.distance = distance;
        this.updateTime = updateTime;
        this.averageTimeProcessed = averageTimeProcessed;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getStandId() {
        return standId;
    }

    public double getDistance() {
        return distance;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }

    public Boolean getAverageTimeProcessed() {
        return averageTimeProcessed;
    }

    public void processed() {
        this.averageTimeProcessed = true;
    }
}
