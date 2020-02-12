package itba.edu.ar.pfexpobeaconbe.api.deviceproximity.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.OffsetDateTime;

@Entity
@IdClass(DeviceProximityId.class)
public class DeviceProximity {

    @Id
    private String deviceId;

    @Id
    private String standId;

    private Double distance;

    private OffsetDateTime updateTime;

    private Long expoHoursId;

    public DeviceProximity() {
    }

    public DeviceProximity(final String theDeviceId, final String theStandId, final Double theDistance,
                           final OffsetDateTime theUpdateTime) {
        deviceId = theDeviceId;
        standId = theStandId;
        distance = theDistance;
        updateTime = theUpdateTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getStandId() {
        return standId;
    }

    public Double getDistance() {
        return distance;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }

    public Long getExpoHoursId() { return expoHoursId; }

    public void setExpoHoursId(final Long theExpoHoursId) {
        this.expoHoursId = theExpoHoursId;
    }
}
