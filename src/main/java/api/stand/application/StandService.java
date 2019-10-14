package api.stand.application;

import api.stand.domain.Stand;
import api.stand.domain.StandRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public class StandService {

    private StandRepository standRepository = new StandRepository();

    @Transactional
    public  ResponseEntity<Stand> getStandByMacAddress(final String macAddress) {

        final Stand stand = standRepository.findByMacAddress(macAddress);
        Validate.notNull(stand,"Stand " + macAddress + " not found");
        return ResponseEntity.ok().body(stand);
    }
}
