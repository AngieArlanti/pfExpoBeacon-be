package api.stats.application;

import java.sql.Time;

public class StandVisitHoursDto {
    private Time from;
    private Time to;
    private Long visits;

    public StandVisitHoursDto(Time from, Time to, Long visits) {
        this.from = from;
        this.to = to;
        this.visits = visits;
    }

    public Time getFrom() {
        return from;
    }

    public Time getTo() {
        return to;
    }

    public Long getVisits() {
        return visits;
    }
}
