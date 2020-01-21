package api.stand.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Entity
public class DeviceProximity {

    @Id
    private String deviceId;

    private String immediateStandId;

    private OffsetDateTime updateTime;

    public DeviceProximity() {
    }

    public DeviceProximity(final String deviceId, final String immediateStandId) {
        this.deviceId = deviceId;
        this.immediateStandId = immediateStandId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getImmediateStandId() {
        return immediateStandId;
    }

    public OffsetDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(OffsetDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
