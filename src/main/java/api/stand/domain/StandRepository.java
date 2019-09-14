package api.stand.domain;

import java.util.concurrent.atomic.AtomicLong;

public class StandRepository {
    private final AtomicLong counter = new AtomicLong();

    public Stand get(final String macAddress) {
        return new Stand(counter.incrementAndGet(), "The first stand",
                "Some stand with a description",
                "0C:F3:EE:08:FC:DD",
                "http://modular360.es/wp-content/uploads/2016/11/Stand-Basic-6-3-caras.png");
    }
}
