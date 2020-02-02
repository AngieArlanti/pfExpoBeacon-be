package api.stats.application;

/**
 * Model for Order Algorithm purposes.
 */
public class StandStatics {

    private String standId;
    private int ranking;
    private long currentCongestion;
    private long historicCongestion;

    public StandStatics(String standId, int ranking, long currentCongestion, long historicCongestion) {
        this.standId = standId;
        this.ranking = ranking;
        this.currentCongestion = currentCongestion;
        this.historicCongestion = historicCongestion;
    }

    public String getStandId() {
        return standId;
    }

    public int getRanking() {
        return ranking;
    }

    public long getCurrentCongestion() {
        return currentCongestion;
    }

    public long getHistoricCongestion() {
        return historicCongestion;
    }
}
