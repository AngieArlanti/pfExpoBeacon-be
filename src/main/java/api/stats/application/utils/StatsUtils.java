package api.stats.application.utils;

import java.util.Collection;

public final class StatsUtils {
    private StatsUtils() {}

    public static long coalesce(final Long value) {
        if (value == null) {
            return 0;
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
