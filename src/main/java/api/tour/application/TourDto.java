package api.tour.application;

import api.stand.domain.Stand;

import java.util.List;

public class TourDto {

    private List<Stand> tour;

    public TourDto() {}

    public TourDto(final List<Stand> tour) {
        this.tour = tour;
    }

    public List<Stand> getTour() {
        return tour;
    }
}
