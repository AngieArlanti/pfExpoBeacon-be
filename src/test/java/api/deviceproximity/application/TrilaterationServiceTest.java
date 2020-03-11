package api.deviceproximity.application;

import org.junit.Ignore;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TrilaterationServiceTest {

    private TrilaterationService trilaterationService = new TrilaterationService();

    @Ignore
    @Test
    public void getLocation() {
        final List<Point> points = new ArrayList<>();
        points.add(new Point(5.0, -6.0, 8.06));
        points.add(new Point(13.0, -15.0, 13.97));
        points.add(new Point(21.0, -3.0, 23.32));

        final Point point = trilaterationService.getLocation(points);
        assertThat(point.getRoundingHalfUpLatitude(), is(-30.3));
        assertThat(point.getRoundingHalfUpLongitude(), is(-9.13));
    }

}

