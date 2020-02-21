package api.deviceproximity.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping(value="/heat_map")
    public ResponseEntity<List<LocationDto>> getHeatMap() {
        return ResponseEntity.ok().body(locationService.getLocations());
    }
}
