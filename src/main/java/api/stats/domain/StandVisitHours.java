package api.stats.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StandVisitHoursId.class)
public class StandVisitHours {
    @Id
    private String standId;

    @Id
    private Long expoHoursId;

    private Long visits;

    public StandVisitHours() {}

    public String getStandId() {
        return standId;
    }

    public Long getExpoHoursId() {
        return expoHoursId;
    }

    public Long getVisits() {
        return visits;
    }
}
