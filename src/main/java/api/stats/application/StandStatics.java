package api.stats.application;

import api.stand.domain.Stand;

/**
 * Model for Order Algorithm purposes.
 */
public abstract class StandStatics {

    protected Stand stand;
    protected double normalizedRanking;
    protected double normalizedCurrentCongestion;
    protected double normalizedOpportunity;
    protected double normalizedDistanceToStartPoint;

    public StandStatics(final Stand stand,
                        final double normalizedRanking,
                        final double normalizedCurrentCongestion,
                        final double normalizedOpportunity,
                        final double normalizedDistanceToStartPoint) {
        this.stand = stand;
        this.normalizedRanking = normalizedRanking;
        this.normalizedCurrentCongestion = normalizedCurrentCongestion;
        this.normalizedOpportunity = normalizedOpportunity;
        this.normalizedDistanceToStartPoint = normalizedDistanceToStartPoint;
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

    public double getNormalizedDistanceToStartPoint() {
        return normalizedDistanceToStartPoint;
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
        return (normalizedRanking * 0.4) - (normalizedCurrentCongestion * 0.25) + (normalizedOpportunity * 0.25)
                - (normalizedDistanceToStartPoint * 0.1);
    }

}
