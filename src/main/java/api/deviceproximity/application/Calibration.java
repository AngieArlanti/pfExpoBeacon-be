package api.deviceproximity.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Calibration {
  private List<Double> center;

  private Calibration(final double zeroMeter, final double halfMeter,
                     final double meter) {
    this.center = new ArrayList<>();
    center.add(zeroMeter);
    center.add(halfMeter);
    center.add(meter);
  }

  public double getCeroMeter(){
    return center.get(0);
  }

  public double getHalfMeter(){
    return center.get(1);
  }

  public double getMeter(){
    return center.get(2);
  }

  public static Map<Point, Calibration> getCalibrationData(){
    final Map<Point, Calibration> calibrationMap = new HashMap<>();

    calibrationMap.put(new Point(-34.641254, -58.401091, 0),
      new Calibration(-3605193.6958379634, -3605192.5093478877,
        -3605190.7752466784));
    calibrationMap.put(new Point(-34.641314, -58.401137, 0),
      new Calibration(-3605193.6958379634, -3605192.5093478877,
        -3605190.7752466784));
    calibrationMap.put(new Point(-34.641306, -58.401073, 0), new Calibration(-3605193.6958379634, -3605192.5093478877,
      -3605190.7752466784));
    calibrationMap.put(new Point(-34.641205, -58.401092, 0), new Calibration(-3605193.6958379634, -3605192.5093478877,
        -3605190.7752466784));

    return calibrationMap;
  }

}
