package api.deviceproximity.application;

import api.deviceproximity.domain.Location;

import java.time.OffsetDateTime;

public class LocationMapper {

    public LocationDto toDtoFromPoint(final Point point){
        return new LocationDto(point.getLongitude(), point.getLatitude());
    }

    public Location toModel(final String deviceId, final LocationDto locationDto) {
        return new Location(deviceId, locationDto.getLongitude(), locationDto.getLatitude(), OffsetDateTime.now());
    }

    public LocationDto toDto(final Location location) {
        return new LocationDto(location.getLongitude(), location.getLatitude());
    }
}
