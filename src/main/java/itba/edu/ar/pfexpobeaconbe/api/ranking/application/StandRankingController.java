  
package itba.edu.ar.pfexpobeaconbe.api.ranking.application;

import itba.edu.ar.pfexpobeaconbe.api.stand.application.StandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StandRankingController {

    @Autowired
    private StandRankingService standRankingService;

    @Autowired
    private StandService standService;

    @PostMapping(value="/stand_ranking")
    public ResponseEntity<Void> save(@RequestBody StandRankingDto standRankingDto) {
        standRankingService.save(standRankingDto);
        return ResponseEntity.ok().build();
    }
}