package api.deviceproximity.application;

import api.deviceproximity.domain.DeviceProximity;
import api.deviceproximity.domain.DeviceProximityRepository;
import api.stand.application.StandService;
import api.stand.domain.Stand;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
public class DeviceProximityService {

    @Autowired
    private DeviceProximityRepository deviceProximityRepository;

    @Autowired
    private StandService standService;

    @Autowired
    private TrilaterationService trilaterationService;

    @Autowired
    private LocationService locationService;

    private DeviceProximityMapper mapper = new DeviceProximityMapper();

    private LocationMapper locationMapper = new LocationMapper();

    public void save(final DeviceProximityDto deviceProximityDto) {
        checkValidStand(deviceProximityDto.getNearbyStandIds());
        final List<DeviceProximity> deviceProximity = mapper.toModel(deviceProximityDto);
        deviceProximityRepository.saveAll(deviceProximity);
        locationService.save(deviceProximityDto.getDeviceId(), getLocation(deviceProximityDto));
    }

    public List<DeviceProximity> listAll() {
        return deviceProximityRepository.findAll();
    }

    public List<DeviceProximity> listAllImmediateStandRegisters() {
        return deviceProximityRepository.findAllInmmediateStandRegisters();
    }

    private void checkValidStand(final List<String> immediateStandIds) {
        final List<Stand> found = standService.findBy(immediateStandIds);
        Validate.notEmpty(found);
    }

    public LocationDto getLocation(final DeviceProximityDto deviceProximityDto) {
        save(deviceProximityDto);
        final Map<String, Double> deviceProximity = deviceProximityDto.getNearbyStands().stream()
                .collect(toMap(NearbyStandDto::getStandId, NearbyStandDto::getDistance));

        final List<Stand> standDtos = standService.findBy(new ArrayList<>(deviceProximity.keySet()));

        final List<Point> points =
                standDtos.stream().map(stand -> new Point(stand.getLatitude(),
                        stand.getLongitude(), deviceProximity.get(stand.getId()))).collect(Collectors.toList());

        final Point point = trilaterationService.getLocation(points);

        return locationMapper.toDtoFromPoint(point);
    }

}
