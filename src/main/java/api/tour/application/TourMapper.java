package api.tour.application;

import api.tour.domain.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourMapper {

    public TourDto toDto(final Tour tour) {
        return new TourDto(tour.getTour());
    }

    public List<TourDto> toDto(final List<Tour> tours) {
        final List<TourDto> tourDtos = new ArrayList<>();

        for (final Tour tour : tours) {
            tourDtos.add(toDto(tour));
        }

        return tourDtos;
    }
}
