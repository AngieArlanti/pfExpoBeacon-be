package api.deviceproximity.application;

import api.deviceproximity.util.CoordinateConversions;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class TrilaterationService {

    private LevenbergMarquardtOptimizer levenbergMarquardtOptimizer = new LevenbergMarquardtOptimizer();

    public Point getLocation(final List<Point> points) {
        if (points.size() < 2) {
            return points.get(0);
        }

      points.sort(Comparator.comparing(Point::getDistance));
        for (Point p : points) {
            System.out.println("LAT: " + p.getLatitude() + " LON: " + p.getLongitude() + " DIST: " + p.getDistance());
        }
        Calibration calibration = Calibration.getCalibrationData().get(points.get(0));
        double z;
        if(points.get(0).getDistance() < 0.5){
            z = calibration.getCeroMeter();
        } else if(points.get(0).getDistance() < 1) {
            z = calibration.getHalfMeter();
        } else {
            z = calibration.getMeter();
        }

        final double[][] positions = getMatrixPosition(points);
        final double[] distances = getDistances(points);

        NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), levenbergMarquardtOptimizer);
        LeastSquaresOptimizer.Optimum optimum = solver.solve();

        return getPoint(optimum.getPoint().toArray(), z);
    }

    private Point getPoint(final double[] arrayPoint, double z) {
        double[] xyz = new double[3];
        xyz[0] =arrayPoint[0];
        xyz[1] =arrayPoint[1];
        xyz[2] =z;
        double[] coordinate = new double[3];
        coordinate=CoordinateConversions.xyzToLatLonDegrees(xyz);
        return new Point(coordinate[0], coordinate[1]);
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
            double[] coordinate = CoordinateConversions.getXYZfromLatLonDegrees(points.get(i).getLatitude(),points.get(i).getLongitude(),0);
            positions[i][0] = coordinate[0];
            positions[i][1] = coordinate[1];
        }
        return positions;
    }


}
