package api.tour.application;

import api.stand.domain.Stand;

import java.util.List;

public class TourInProgressDto {

    private Stand currentStand;
    private List<Stand> pendingStands;

    public TourInProgressDto(final Stand currentStand, final List<Stand> pendingStands) {
        this.currentStand = currentStand;
        this.pendingStands = pendingStands;
    }

    public Stand getCurrentStand() {
        return currentStand;
    }

    public List<Stand> getPendingStands() {
        return pendingStands;
    }
}
