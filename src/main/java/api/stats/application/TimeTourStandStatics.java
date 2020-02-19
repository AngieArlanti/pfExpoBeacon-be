package api.stats.application;

import api.stand.domain.Stand;

public class TimeTourStandStatics extends StandStatics {

    public TimeTourStandStatics(final Stand stand, final double normalizedRanking, final double normalizedCurrentCongestion,
                                final double normalizedOpportunity, final double normalizedDistanceToStartPoint) {
        super(stand, normalizedRanking, normalizedCurrentCongestion, normalizedOpportunity,
                normalizedDistanceToStartPoint);
    }
}
