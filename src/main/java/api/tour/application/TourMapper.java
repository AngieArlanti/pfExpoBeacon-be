package api.tour.application;

import api.stand.domain.Stand;

import java.util.ArrayList;
import java.util.List;

class TourMapper {

    TourDto toDto(final Tour tour) {
        return new TourDto(tour.getStands());
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
