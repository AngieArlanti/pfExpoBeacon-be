package itba.edu.ar.pfexpobeaconbe.api.stats.application.utils;

import itba.edu.ar.pfexpobeaconbe.api.stats.application.StatsInterval;

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

    public static StatsInterval getStatsInterval(final Collection<Long> values) {
        return new StatsInterval(values);
    }

    public static double getNormalizedValue(final Long value, final Long min, final Long max) {
        return ((double) (value - min) / Math.abs(max - min));
    }
}