package api.tour.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TourController {

    @Autowired
    private TourService tourService;

    @GetMapping("/tour/no_lines")
    public ResponseEntity<TourDto> getTourWithoutLines() {
        return ResponseEntity.ok().body(tourService.getTourWithoutLines());
    }

    @PostMapping("/tour/no_lines")
    public ResponseEntity<TourDto> getNextBestStandToVisitTour(@RequestBody final TourInProgressDto tourInProgressDto) {
        return ResponseEntity.ok().body(tourService.getNextBestStandToVisitTour(tourInProgressDto.getCurrentStand(),
                tourInProgressDto.getPendingStands()));
    }

    @GetMapping("/tour/time_limited")
    public ResponseEntity<TourDto> getTimeLimitedTour(@RequestParam(name="time_limit",
            required=true) final Double timeLimit ) {
        return ResponseEntity.ok().body(tourService.getTimeLimitedTour(timeLimit));
    }

    @GetMapping("/tour/top_three")
    public ResponseEntity<List<TourDto>> getTopThreeTours() {
        return ResponseEntity.ok().body(tourService.getTopThreeTours());
    }
}
