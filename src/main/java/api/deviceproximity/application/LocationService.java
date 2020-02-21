package api.deviceproximity.application;

import api.deviceproximity.domain.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    private LocationMapper locationMapper = new LocationMapper();

    public void save(final String deviceId, final LocationDto locationDto) {
        locationRepository.save(locationMapper.toModel(deviceId, locationDto));
    }

    public List<LocationDto> getLocations() {
        final OffsetDateTime now = OffsetDateTime.now();
        return locationRepository.findByUpdateTimeBetween(now.minus(10, ChronoUnit.MINUTES), now)
                .stream().map(location -> locationMapper.toDto(location))
                .collect(Collectors.toList());
    }


}
