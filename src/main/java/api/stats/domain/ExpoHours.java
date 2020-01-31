package api.stats.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class ExpoHours {

    @Id
    private Long id;
    @Column(name = "start", nullable = false)
    private Time from;
    @Column(name = "finish", nullable = false)
    private Time to;

    public ExpoHours() {}

    public Long getId() {
        return id;
    }

    public Time getFrom() {
        return from;
    }

    public Time getTo() {
        return to;
    }
}
