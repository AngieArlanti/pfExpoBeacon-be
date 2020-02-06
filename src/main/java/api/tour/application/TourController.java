package api.tour.application;

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
    public ResponseEntity<TourDto> getTourWithoutLines() {
        return ResponseEntity.ok().body(tourService.getTourWithoutLines());
    }

    @RequestMapping("/tour/time_limited")
    public ResponseEntity<TourDto> getTimeLimitedTour(@RequestParam(name="time_limit",
            required=true) final Double timeLimit ) {
        return ResponseEntity.ok().body(tourService.getTimeLimitedTour(timeLimit));
    }

    @RequestMapping("/tour/top_three")
    public ResponseEntity<List<TourDto>> getTopThreeTours() {
        return ResponseEntity.ok().body(tourService.getTopThreeTours());
    }
}
