package api.stand.application;

import api.stand.domain.Stand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StandController {
    private StandService standService = new StandService();

    @RequestMapping("/stands")
    public ResponseEntity<Stand> findBy(@RequestParam(name="id",
      required=true) String id) {
       return standService.findBy(id);
    }

    @RequestMapping("/stands/list")
    public ResponseEntity<List<Stand>> listOrderedByRanking() {
        return standService.listOrderedByRanking();
    }
}