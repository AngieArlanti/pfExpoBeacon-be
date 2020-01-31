package api.stats.domain;

import java.io.Serializable;
import java.util.Objects;

public class StandVisitHoursId implements Serializable {

    private String standId;
    private Long expoHoursId;

    public StandVisitHoursId() {
    }

    public StandVisitHoursId(String standId, Long expoHoursId) {
        this.standId = standId;
        this.expoHoursId = expoHoursId;
    }

    public String getStandId() {
        return standId;
    }

    public Long getExpoHoursId() {
        return expoHoursId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandVisitHoursId that = (StandVisitHoursId) o;
        return standId.equals(that.standId) &&
                expoHoursId.equals(that.expoHoursId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(standId, expoHoursId);
    }
}
