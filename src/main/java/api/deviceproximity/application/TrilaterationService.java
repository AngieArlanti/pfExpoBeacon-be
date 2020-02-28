package api.deviceproximity.application;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrilaterationService {

    private LevenbergMarquardtOptimizer levenbergMarquardtOptimizer = new LevenbergMarquardtOptimizer();

    public Point getLocation(final List<Point> points) {
        if (points.size() < 2) {
            return points.get(0);
        }

        final double[][] positions = getMatrixPosition(points);
        final double[] distances = getDistances(points);

        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), levenbergMarquardtOptimizer);
        LeastSquaresOptimizer.Optimum optimum = solver.solve();

        return getPoint(optimum.getPoint().toArray());
    }

    private Point getPoint(final double[] arrayPoint) {
        return new Point(arrayPoint[0], arrayPoint[1]);
    }

    private double[] getDistances(final List<Point> points) {
        double[] distances = new double[points.size()];
        for (int i = 0; i < points.size(); i++) {
            distances[i] = points.get(i).getDistance();
        }
        return distances;
    }

    private double[][] getMatrixPosition(final List<Point> points) {
        double[][] positions = new double[points.size()][2];
        for (int i = 0; i < points.size(); i++) {
            positions[i][0] = points.get(i).getLatitude();
            positions[i][1] = points.get(i).getLongitude();
        }
        return positions;
    }


}
