package api.stats.application.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
public class StatsUtilsTest {

    @Test
    public void getNormalizedValue() {
        double normalize = StatsUtils.getNormalizedValue(5L, 0L, 7L);
        assertThat(normalize, is(0.7142857142857143));
    }

    @Test
    public void getMinMax() {
        List<Long> values = new ArrayList<>();
        values.add(0L);
        values.add(5L);
        values.add(7L);

        StatsInterval statsInterval = StatsUtils.getStatsInterval(values);

        assertThat(statsInterval.getMin(), is(0L));
        assertThat(statsInterval.getMax(), is(7L));

    }
}
