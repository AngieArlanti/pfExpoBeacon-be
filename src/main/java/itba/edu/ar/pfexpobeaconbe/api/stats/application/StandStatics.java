package itba.edu.ar.pfexpobeaconbe.api.stats.application;

import itba.edu.ar.pfexpobeaconbe.api.stand.domain.Stand;

/**
 * Model for Order Algorithm purposes.
 */
public class StandStatics {

    private Stand stand;
    private double normalizedRanking;
    private double normalizedCurrentCongestion;
    private double normalizedOpportunity;

    public StandStatics(Stand stand,
                        double normalizedRanking,
                        double normalizedCurrentCongestion,
                        double normalizedOpportunity) {
        this.stand = stand;
        this.normalizedRanking = normalizedRanking;
        this.normalizedCurrentCongestion = normalizedCurrentCongestion;
        this.normalizedOpportunity = normalizedOpportunity;
    }

    public Stand getStand() {
        return stand;
    }

    public double getNormalizedRanking() {
        return normalizedRanking;
    }

    public double getNormalizedCurrentCongestion() {
        return normalizedCurrentCongestion;
    }

    public double getNormalizedOpportunity() {
        return normalizedOpportunity;
    }

    /**
     * // En éste caso te conviene mandarlo al 1. Por eso la congestion tiene una ponderación mayor que la oportunidad.
     * //1. 5 rank - cong 0 - hist 5
     * //2. 5 - 5 - 10
     * // En éste caso sucede lo mismo:
     * //1. 4 - 0 - 0
     * //2. 4 - 9 - 10
     *
     * @return
     */
    public double getOrderCriteria() {
        //Priority is given to Ranking being 3 or higher.
        if (normalizedRanking >= 0.6) {
            return (normalizedRanking * 0.4) - (normalizedCurrentCongestion * 0.4) + (normalizedOpportunity * 0.2);
        } else {
            return (normalizedRanking * 0.2) - (normalizedCurrentCongestion * 0.5) + (normalizedOpportunity * 0.3);
        }
    }

}
