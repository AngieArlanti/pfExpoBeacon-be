package api.stats.application;

import api.stats.domain.ExpoHours;
import api.stats.domain.StandVisitHours;

import java.util.ArrayList;
import java.util.List;

public class StandVisitHoursMapper {

    public StandVisitHoursDto toDto(StandVisitHours standVisitHours, ExpoHours expoHours) {
        return new StandVisitHoursDto(expoHours.getFrom(),
                expoHours.getTo(), standVisitHours.getVisits());
    }

    public List<StandVisitHoursDto> toDto(final List<StandVisitHours> standVisitHours,
                                          final List<ExpoHours> expoHours) {
        final List<StandVisitHoursDto> standVisitHoursDtos = new ArrayList<>();
        for (StandVisitHours standVisitHour : standVisitHours) {
            for (ExpoHours eh : expoHours) {
                if(standVisitHour.getExpoHoursId().equals(eh.getId())){
                    standVisitHoursDtos.add(toDto(standVisitHour, eh));
                }
            }
        }
        return standVisitHoursDtos;
    }
}
