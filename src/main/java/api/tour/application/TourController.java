package api.tour.application;

import api.stand.domain.Stand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TourController {

    @Autowired
    private TourService tourService;

    @RequestMapping("/tour/no_lines")
    public ResponseEntity<List<Stand>> getTourWithoutLines() {
        return ResponseEntity.ok().body(tourService.orderStands());
    }

    @RequestMapping("/tour/time_limited")
    public ResponseEntity<List<Stand>> getTimeLimitedTour(@RequestParam(name="time_limit",
            required=true) final Double timeLimit ) {
        return ResponseEntity.ok().body(tourService.getTimeLimitedTour(timeLimit));
    }
}
