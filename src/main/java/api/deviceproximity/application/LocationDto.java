package api.deviceproximity.application;

public class LocationDto {

    private double longitude;

    private double latitude;

    public LocationDto() {
    }

    public LocationDto(final double longitude, final double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
