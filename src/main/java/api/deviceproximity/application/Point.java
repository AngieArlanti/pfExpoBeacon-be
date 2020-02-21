package api.deviceproximity.application;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
}
