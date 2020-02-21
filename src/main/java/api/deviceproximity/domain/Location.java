package api.deviceproximity.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class Location {

    @Id
    private String deviceId;

    private double longitude;

    private double latitude;

    private OffsetDateTime updateTime;

    public Location() {
    }

    public Location(final String deviceId, final double longitude,
                    final double latitude,
                    final OffsetDateTime updateTime) {
        this.deviceId = deviceId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.updateTime = updateTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }
}
