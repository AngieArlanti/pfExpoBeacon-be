package api.stand.application;

import api.stand.domain.Stand;
import api.stand.domain.StandRepository;

public class StandService {

    private final StandRepository repository = new StandRepository();

    public Stand getStand(final String macAddress) {
        return repository.get(macAddress);
    }
}
