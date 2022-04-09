package api.deviceproximity.application;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Ignore
public class TrilaterationServiceTest {

    private TrilaterationService trilaterationService = new TrilaterationService();

    @Test
    public void solveTrilateration() {
        double[][] positions = new double[3][2];
        double[] distances = new double[3];

        positions[0][0] = 0;
        positions[0][1] = 0;
        positions[1][0] = 0;
        positions[1][1] = 2;
        positions[2][0] = 4;
        positions[2][1] = 0;

        distances[0]=Math.sqrt(5);
        distances[1]=Math.sqrt(5);
        distances[2]=Math.sqrt(5);

        double[] trilaterationCoordinates = trilaterationService.solveTrilateration(positions, distances);

        assertThat(trilaterationCoordinates[0], is(2.0));
        assertThat(Math.round(trilaterationCoordinates[1]), is(1L));
    }

    @Test
    public void getLocation() {
        Point p1 = new Point(-34.519400, -58.509615, 0.063);
        Point p2 = new Point(-34.518744, -58.510115, 0.064);
        Point p3 = new Point(-34.518933, -58.508761, 0.059);

        List<Point> points = new ArrayList<>();

        points.add(p1);
        points.add(p2);
        points.add(p3);

        Point location = trilaterationService.getLocation(points);

        assertThat(location.getLatitude(), is(-34.51896737833606));
        assertThat(location.getLongitude(), is(-58.50945214853153));
    }
}

