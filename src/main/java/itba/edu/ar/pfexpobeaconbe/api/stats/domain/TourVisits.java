package itba.edu.ar.pfexpobeaconbe.api.stats.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TourVisits {

    @Id
    private String tour;
    private Long visits;

    public TourVisits() {}

    public TourVisits(final String tour, final Long visits) {
        this.tour = tour;
        this.visits = visits;
    }

    public String getTour() {
        return tour;
    }

    public Long getVisits() {
        return visits;
    }
}
