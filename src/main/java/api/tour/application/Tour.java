package api.tour.application;

import api.stand.domain.Stand;

import java.util.List;

public class Tour {

    private List<Stand> stands;
    private long visits;

    public Tour() {
    }

    public Tour(final List<Stand> stands, final long visits) {
        this.stands = stands;
        this.visits = visits;
    }

    public List<Stand> getStands() {
        return stands;
    }

    public long getVisits() {
        return visits;
    }
}
