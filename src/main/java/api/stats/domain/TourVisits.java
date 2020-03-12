package api.stats.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TourVisits {

    @Id
    private String standIds;
    private long visits;

    public TourVisits() {}

    public TourVisits(final String standIds, final long visits) {
        this.standIds = standIds;
        this.visits = visits;
    }

    public String getStandIds() {
        return standIds;
    }

    public long getVisits() {
        return visits;
    }
}
