package api.deviceproximity.application;

public class LocationMapper {

    public LocationDto toDto(final Point point){
        return new LocationDto(point.getLongitude(), point.getLatitude());
    }
}
