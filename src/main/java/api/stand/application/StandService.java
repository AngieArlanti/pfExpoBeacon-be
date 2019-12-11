package api.stand.application;

import api.stand.domain.Stand;
import api.stand.domain.StandRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class StandService {

    /** The Stand's Repository. Never null.
     */
    private StandRepository standRepository = new StandRepository();

    /** Returns a Stand with the given id.
     *
     * @param id Stand's id. MUST be the natural id beacon's MAC ADDRESS.
     * @return a Stand.
     */
    public  ResponseEntity<Stand> findBy(final String id) {
        final Stand stand = standRepository.findBy(id);
        Validate.notNull(stand,"Stand " + id + " not found");
        return ResponseEntity.ok().body(stand);
    }

    /** Returns a Stand's List using the given Stands' ids.
     *
     * @param ids the Stands's ids.
     * @return a Stand's list.
     * */
    public  ResponseEntity<List<Stand>>  findBy(final List<String> ids) {
        final List<Stand> stands = standRepository.findBy(ids);
        Validate.notEmpty(stands,"Stands " + ids + " not found");
        Validate.isTrue(stands.size() == ids.size(), "Stands not found");
        return ResponseEntity.ok().body(stands);
    }

    /** Returns all the available Stands ordered by ranking.
     *
     * @return a Stand's list ordered by ranking.
     */
    public ResponseEntity<List<Stand>> listOrderedByRanking() {
        final List<Stand> list = standRepository.findOrderedByRanking();
        return ResponseEntity.ok().body(list);
    }
}
