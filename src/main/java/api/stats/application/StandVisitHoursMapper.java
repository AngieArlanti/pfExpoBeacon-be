package api.stats.application;

import api.stats.domain.ExpoHours;
import api.stats.domain.StandVisitHours;

public class StandVisitHoursMapper {

    public StandVisitHoursDto toDto(StandVisitHours standVisitHours, ExpoHours expoHours) {
        return new StandVisitHoursDto(expoHours.getFrom(),
                expoHours.getTo(), standVisitHours.getVisits());
    }
}
