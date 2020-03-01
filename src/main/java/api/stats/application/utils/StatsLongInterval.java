package api.stats.application.utils;

import java.util.Collection;

import static api.stats.application.utils.StatsUtils.coalesce;

public class StatsLongInterval {
    private long min;
    private long max;

    public StatsLongInterval(final long min, final long max) {
        this.min = min;
        this.max = max;
    }

    public StatsLongInterval(final Collection<Long> values) {
        values.stream().min(Long::compareTo).ifPresent(aLong -> min = coalesce(aLong));
        values.stream().max(Long::compareTo).ifPresent(aLong -> max = coalesce(aLong));
    }

    public long getMin() {
        return min;
    }

    public long getMax() {
        return max;
    }
}
