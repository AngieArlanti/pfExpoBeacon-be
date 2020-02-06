package api.stats.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatsController {

    @Autowired
    private StatsService statsService;

    @RequestMapping("/stats/stand_histogram")
    public ResponseEntity<List<StandVisitHoursDto>> findBy(@RequestParam(name="stand_id",
            required=true) String standId) {
        return ResponseEntity.ok().body(statsService.getStandVisitHours(standId));
    }
}
