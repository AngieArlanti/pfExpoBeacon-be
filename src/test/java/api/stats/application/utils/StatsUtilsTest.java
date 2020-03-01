package api.stats.application.utils;

import api.position.domain.Position;
import api.stand.domain.Stand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        StatsLongInterval statsInterval = StatsUtils.getStatsLongInterval(values);

        assertThat(statsInterval.getMin(), is(0L));
        assertThat(statsInterval.getMax(), is(7L));

    }

    @Test
    public void getHaversineDistance() {
        final Position position = new Position( -58.401466, -34.640419);
        final Stand stand = mock(Stand.class);
        when(stand.getLatitude()).thenReturn(-34.640271);
        when(stand.getLongitude()).thenReturn(-58.401515);
        final double distance = StatsUtils.getHaversineDistance(position, stand);

        assertThat(distance, is(17.075562635395443));
    }
}
