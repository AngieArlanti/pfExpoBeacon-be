package api.stand.application;

import api.stand.domain.Stand;
import api.stand.domain.StandRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public class StandService {

    private StandRepository standRepository = new StandRepository();

    @Transactional
    public  ResponseEntity<Stand> findBy(final String id) {

        final Stand stand = standRepository.findBy(id);
        Validate.notNull(stand,"Stand " + id + " not found");
        return ResponseEntity.ok().body(stand);
    }
}
