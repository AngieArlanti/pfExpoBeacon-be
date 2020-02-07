package itba.edu.ar.pfexpobeaconbe.api.ranking.application;

public class StandRankingDto {

    private String standId;

    private String deviceId;

    private int ranking;

    public StandRankingDto() {
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