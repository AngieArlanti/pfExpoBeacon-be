package api.stats.application.utils;

import api.position.domain.Position;
import api.stand.domain.Stand;

import java.awt.geom.Point2D;
import java.util.Collection;

public final class StatsUtils {
    private StatsUtils() {}

    public static long coalesce(final Long value) {
        if (value == null) {
            return 0;
        }
        return value;
    }

    public static Double coalesce(final Double value) {
        if (value == null) {
            return 0.0;
        }
        return value;
    }

    public static StatsDoubleInterval getStatsDoubleInterval(final Collection<Double> values) {
        return new StatsDoubleInterval(values);
    }

    public static StatsLongInterval getStatsLongInterval(final Collection<Long> values) {
        return new StatsLongInterval(values);
    }

    public static double getNormalizedValue(final Long value, final Long min, final Long max) {
        return (max - min) == 0.0 ? 0.0 : ((double) (value - min) / Math.abs(max - min));
    }

    public static double getNormalizedValue(final Double value, final Double min, final Double max) {
        return (max - min) == 0.0 ? 0.0 : ((value - min) / Math.abs(max - min));
    }

    public static double getLinearDistance(final Position startPosition, final Stand stand) {
        return Point2D.distance(startPosition.getLongitude(),
                startPosition.getLatitude(), stand.getLongitude(), stand.getLatitude());
    }
}
