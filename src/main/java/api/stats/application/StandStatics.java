package api.stats.application;

/**
 * Model for Order Algorithm purposes.
 */
public class StandStatics {

    private String standId;
    private int ranking;
    private Long currentCongestion;
    private Long historicCongestion;

    public StandStatics(String standId, int ranking, Long currentCongestion, Long historicCongestion) {
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

    public Long getCurrentCongestion() {
        return currentCongestion;
    }

    public Long getHistoricCongestion() {
        return historicCongestion;
    }
}
