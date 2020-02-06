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

    private TourMapper tourMapper = new TourMapper();

    @RequestMapping("/tour/no_lines")
    public ResponseEntity<List<Stand>> getTourWithoutLines() {
        return ResponseEntity.ok().body(tourService.getTourWithoutLines());
    }

    @RequestMapping("/tour/time_limited")
    public ResponseEntity<List<Stand>> getTimeLimitedTour(@RequestParam(name="time_limit",
            required=true) final Double timeLimit ) {
        return ResponseEntity.ok().body(tourService.getTimeLimitedTour(timeLimit));
    }

    @RequestMapping("/tour/top_three")
    public ResponseEntity<List<TourDto>> getTopThreeTours() {
        final List<TourDto> tourDtos = tourMapper.toDto(tourService.getTopThreeTours());
        return ResponseEntity.ok().body(tourDtos);
    }
}
