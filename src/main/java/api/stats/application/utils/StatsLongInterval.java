package api.stats.application.utils;

import java.util.Collection;

import static api.stats.application.utils.StatsUtils.coalesce;

public class StatsLongInterval {
    private Long min;
    private Long max;

    public StatsLongInterval(final Long min, final Long max) {
        this.min = min;
        this.max = max;
    }

    public StatsLongInterval(final Collection<Long> values) {
        values.stream().min(Long::compareTo).ifPresent(aLong -> min = coalesce(aLong));
        values.stream().max(Long::compareTo).ifPresent(aLong -> max = coalesce(aLong));
    }

    public Long getMin() {
        return min;
    }

    public Long getMax() {
        return max;
    }
}
