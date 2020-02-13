package api.stats.application;

import api.stand.domain.Stand;

public class PopularTourStandStatics extends StandStatics {

    public PopularTourStandStatics(final Stand stand, final double normalizedCurrentCongestion,
                                   final double normalizedOpportunity, final double normalizedDistanceToStartPoint) {
        super(stand, 0, normalizedCurrentCongestion,
                normalizedOpportunity, normalizedDistanceToStartPoint);
    }
}
