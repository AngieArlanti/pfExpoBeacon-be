package api.deviceproximity.application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Point {

    private double latitude;
    private double longitude;
    private Double distance;

    public Point(final double latitude, final double longitude, final double distance) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.distance = distance;
    }

    public Point(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getRoundingHalfUpLongitude(){
        return getRoundingHalfUp(longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getRoundingHalfUpLatitude(){
        return getRoundingHalfUp(latitude);
    }

    public double getDistance() {
        return distance;
    }

    private double getRoundingHalfUp(final double number) {
        BigDecimal bd = new BigDecimal(number);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.latitude, latitude) == 0 &&
          Double.compare(point.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
