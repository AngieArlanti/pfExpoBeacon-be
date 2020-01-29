package api.stand.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StandRankingId.class)
public class StandRanking {

    @Id
    private String standId;

    @Id
    private String deviceId;

    private int ranking;

    public StandRanking() {
    }

    public StandRanking(final String standId, final String deviceId, final int ranking) {
        this.standId = standId;
        this.deviceId = deviceId;
        this.ranking = ranking;
    }

    public String getStandId() {
        return standId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public int getRanking() {
        return ranking;
    }
}