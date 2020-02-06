package api.tour.domain;

import api.stand.domain.Stand;

import java.util.List;

public class Tour {

    private List<Stand> tour;
    private Long visits;

    public Tour(final List<Stand> tour, final Long visits) {
        this.tour = tour;
        this.visits = visits;
    }

    public List<Stand> getTour() {
        return tour;
    }

    public Long getVisits() {
        return visits;
    }
}
