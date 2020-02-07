package itba.edu.ar.pfexpobeaconbe.api.ranking.domain;

import java.io.Serializable;
import java.util.Objects;

public class StandRankingId implements Serializable {

    private String standId;
    private String deviceId;

    public StandRankingId(String standId, String deviceId) {
        this.standId = standId;
        this.deviceId = deviceId;
    }

    public String getStandId() {
        return standId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandRankingId that = (StandRankingId) o;
        return standId.equals(that.standId) &&
                deviceId.equals(that.deviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(standId, deviceId);
    }
}
