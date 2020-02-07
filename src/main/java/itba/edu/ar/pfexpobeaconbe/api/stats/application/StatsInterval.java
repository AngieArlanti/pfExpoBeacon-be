package itba.edu.ar.pfexpobeaconbe.api.stats.application;

import itba.edu.ar.pfexpobeaconbe.api.stats.application.utils.StatsUtils;

import java.util.Collection;

import static itba.edu.ar.pfexpobeaconbe.api.stats.application.utils.StatsUtils.coalesce;

public class StatsInterval {
    private Long min;
    private Long max;

    public StatsInterval(Long min, Long max) {
        this.min = min;
        this.max = max;
    }

    public StatsInterval(final Collection<Long> values) {
        values.stream().min(Long::compareTo).ifPresent(aLong -> min = StatsUtils.coalesce(aLong));
        values.stream().max(Long::compareTo).ifPresent(aLong -> max = StatsUtils.coalesce(aLong));
    }

    public Long getMin() {
        return min;
    }

    public Long getMax() {
        return max;
    }
}
