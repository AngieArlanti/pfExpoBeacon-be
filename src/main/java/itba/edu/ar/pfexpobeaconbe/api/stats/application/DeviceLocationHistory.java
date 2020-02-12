package itba.edu.ar.pfexpobeaconbe.api.stats.application;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "device_location_history")
public class DeviceLocationHistory {

    @Id
    @GeneratedValue
    private Long id;
    private String deviceId;
    private String standId;
    private double distance;
    private OffsetDateTime updateTime;
    private Boolean averageTimeProcessed;
    private Boolean histogramProcessed;
    private Long expoHoursId;

    public DeviceLocationHistory() {
    }

    public DeviceLocationHistory(String deviceId, String standId, double distance, OffsetDateTime updateTime, Boolean averageTimeProcessed, Boolean histogramProcessed, Long expoHoursId) {
        this.deviceId = deviceId;
        this.standId = standId;
        this.distance = distance;
        this.updateTime = updateTime;
        this.averageTimeProcessed = averageTimeProcessed;
        this.histogramProcessed = histogramProcessed;
        this.expoHoursId = expoHoursId;
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

    public Long getExpoHoursId() {
        return expoHoursId;
    }

    public Boolean getAverageTimeProcessed() {
        return averageTimeProcessed;
    }

    public Boolean getHistogramProcessed() {
        return histogramProcessed;
    }

    public void averageTimeTaskProcessed() {
        this.averageTimeProcessed = true;
    }

    public void histogramTaskProcessed() {
        this.histogramProcessed = true;
    }
}
