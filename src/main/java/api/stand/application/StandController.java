package api.stand.application;

import api.stand.domain.Stand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StandController {

    @Autowired
    private StandService standService;

    /**
     * Returns a Stand with the given id.
     *
     * Example of the use:
     *
     * http://localhost:8080/stands?id=0C:F3:EE:08:FC:DD
     *
     * @param id Stand's id. MUST be the natural id beacon's MAC ADDRESS.
     * @return a Stand.
     */
    @RequestMapping("/stands")
    public ResponseEntity<Stand> findBy(@RequestParam(name="id",
      required=true) String id) {
       return ResponseEntity.ok().body(standService.findBy(id));
    }

    /**
     * Returns a Stand's List using the given Stands' ids.
     *
     * Example of the use:
     *
     * As we are not using a wrapping object to map the List<String>, to use this service, the argument
     * Json MUST have this form:
     *
     * [
     *   "0C:F3:EE:08:FC:DD",
     *   "0C:F3:EE:04:19:2F",
     *   "0C:F3:EE:04:18:A0"
     * ]
     *
     * @param ids the Stands's ids.
     * @return a Stand's list.
     */
    @PostMapping(value="/stands")
    public ResponseEntity<List<Stand>> findBy(@RequestBody List<String> ids) {
        return ResponseEntity.ok().body(standService.findBy(ids));
    }

    /**
     * Returns all the available Stands ordered by ranking.
     *
     * Example of the use:
     *
     * http://localhost:8080/stands/list
     *
     * @return a Stand's list ordered by ranking.
     */
    @RequestMapping("/stands/list")
    public ResponseEntity<List<Stand>> listOrderedByRanking() {
        return ResponseEntity.ok().body(standService.listOrderedByRanking());
    }
}