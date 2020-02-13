package api.stats.application.utils;

import java.util.Collection;

import static api.stats.application.utils.StatsUtils.coalesce;

public class StatsDoubleInterval {
    private Double min;
    private Double max;

    public StatsDoubleInterval(final Double min, final Double max) {
        this.min = min;
        this.max = max;
    }

    public StatsDoubleInterval(final Collection<Double> values) {
        values.stream().min(Double::compareTo).ifPresent(aLong -> min = coalesce(aLong));
        values.stream().max(Double::compareTo).ifPresent(aLong -> max = coalesce(aLong));
    }

    public Double getMin() {
        return min;
    }

    public Double getMax() {
        return max;
    }
}
