package itba.edu.ar.pfexpobeaconbe.api.tour.application;

import itba.edu.ar.pfexpobeaconbe.api.stand.domain.Stand;
import itba.edu.ar.pfexpobeaconbe.api.tour.domain.Tour;

import java.util.ArrayList;
import java.util.List;

class TourMapper {

    TourDto toDto(final Tour tour) {
        return new TourDto(tour.getTour());
    }

    List<TourDto> toDto(final List<Tour> tours) {
        final List<TourDto> tourDtos = new ArrayList<>();

        for (final Tour tour : tours) {
            tourDtos.add(toDto(tour));
        }
        return tourDtos;
    }

    TourDto toDtoFromStands(final List<Stand> stands) {
        return new TourDto(stands);
    }
}
