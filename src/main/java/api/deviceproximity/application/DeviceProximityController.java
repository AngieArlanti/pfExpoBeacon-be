package api.deviceproximity.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeviceProximityController {

    @Autowired
    private DeviceProximityService deviceProximityService;

    @Autowired
    private LocationService locationService;

    @PostMapping(value="/device_proximity")
    public ResponseEntity<Void> save(@RequestBody DeviceProximityDto deviceProximityDto) {
        deviceProximityService.save(deviceProximityDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value="/get_location")
    public ResponseEntity<LocationDto> getLocation(@RequestBody DeviceProximityDto deviceProximityDto) {
        return ResponseEntity.ok()
                .body(deviceProximityService.getLocation(deviceProximityDto));
    }

    @GetMapping(value="/heat_map")
    public ResponseEntity<List<LocationDto>> getHeatMap() {
        return ResponseEntity.ok().body(locationService.getLocations());
    }
}
