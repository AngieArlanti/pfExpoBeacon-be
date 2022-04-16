package api.deviceproximity.application;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.lang3.Validate;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

import java.util.List;

import static api.deviceproximity.application.SphericalMercatorProjection.ecefToLocation;
import static api.deviceproximity.application.SphericalMercatorProjection.locationToEcef;

@Service
public class TrilaterationService {

  private LevenbergMarquardtOptimizer levenbergMarquardtOptimizer = new LevenbergMarquardtOptimizer();

  public Point getLocation(final List<Point> points) {
    Validate.isTrue(points.size() >= 2, "At least two points must be given to calculate location.");

    final double[][] positions = getMatrixPosition(points);
    final double[] distances = getDistances(points);

    double[] trilaterationCoordinates = solveTrilateration(positions, distances);

    return convertArrayIntoPoint(trilaterationCoordinates);
  }

  double[] solveTrilateration(double[][] positions, double[] distances) {
      NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
          new TrilaterationFunction(positions, distances), levenbergMarquardtOptimizer);
      LeastSquaresOptimizer.Optimum optimum = solver.solve();
    return optimum.getPoint().toArray();
  }

  private Point convertArrayIntoPoint(final double[] arrayPoint) {
    double[] xyz = new double[3];
    xyz[0] = arrayPoint[0];
    xyz[1] = arrayPoint[1];
    xyz[2] = arrayPoint[2];
    double[] coordinate = ecefToLocation(xyz);
    return new Point(coordinate[0], coordinate[1]);
  }

  private double[] getDistances(final List<Point> points) {
    double[] distances = new double[points.size()];
    for (int i = 0; i < points.size(); i++) {
      double distance = points.get(i).getDistance();
      Validate.isTrue(distance > 0, "Distances must be positive values.");
      distances[i] = distance;

    }
    return distances;
  }

  private double[][] getMatrixPosition(final List<Point> points) {
    double[][] positions = new double[points.size()][3];
    for (int i = 0; i < points.size(); i++) {
      double[] coordinate = locationToEcef(new double[]{points.get(i).getLatitude(), points.get(i).getLongitude(),
          points.get(i).getHeight()});
      positions[i][0] = coordinate[0];
      positions[i][1] = coordinate[1];
      positions[i][2] = coordinate[2];
    }
    return positions;
  }
}
