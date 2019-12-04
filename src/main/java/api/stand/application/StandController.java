package api.stand.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import api.stand.domain.Stand;

@RestController
public class StandController {
    private StandService standService = new StandService();

    @RequestMapping("/stands")
    public ResponseEntity<Stand> findBy(@RequestParam(name="id",
      required=true) String id) {
       return standService.findBy(id);
    }
}