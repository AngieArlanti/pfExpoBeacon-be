package api.stats.application.utils;

import api.position.domain.Position;
import api.stand.domain.Stand;

import java.util.Collection;

public final class StatsUtils {
    private StatsUtils() {
    }

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

    public static double getHaversineDistance(final Position startPosition, final Stand stand) {
        return getDistanceBetweenPoints(startPosition.getLatitude(), startPosition.getLongitude(),
                stand.getLatitude(), stand.getLongitude());
    }

    private static double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    private static double getDistanceBetweenPoints(final double lat1, final double long1,
                                                   final double lat2, final double long2) {
        final int R = 6378137; // Radious of the earth
        final double latDistance = degreesToRadians(lat2 - lat1);
        final double lonDistance = degreesToRadians(long2 - long1);
        final double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(degreesToRadians(lat1)) * Math.cos(degreesToRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        return R * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }
}
