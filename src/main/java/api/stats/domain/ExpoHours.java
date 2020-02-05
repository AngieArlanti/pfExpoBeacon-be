package api.stats.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class ExpoHours {

    @Id
    private Long id;
    @Column(name = "start", nullable = false)
    private LocalTime from;
    @Column(name = "finish", nullable = false)
    private LocalTime to;

    public ExpoHours() {}

    public Long getId() {
        return id;
    }

    public LocalTime getFrom() {
        return from;
    }

    public LocalTime getTo() {
        return to;
    }
}
