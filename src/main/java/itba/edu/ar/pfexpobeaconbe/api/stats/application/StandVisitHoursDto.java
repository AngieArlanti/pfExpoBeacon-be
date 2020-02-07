package itba.edu.ar.pfexpobeaconbe.api.stats.application;

import java.time.LocalTime;

public class StandVisitHoursDto {
    private String standId;
    private LocalTime from;
    private LocalTime to;
    private Long visits;

    public StandVisitHoursDto(final String standId, final LocalTime from, final LocalTime to, final Long visits) {
        this.standId = standId;
        this.from = from;
        this.to = to;
        this.visits = visits;
    }

    public String getStandId() {
        return standId;
    }

    public LocalTime getFrom() {
        return from;
    }

    public LocalTime getTo() {
        return to;
    }

    public Long getVisits() {
        return visits;
    }

    public boolean matchesCurrentTime() {
        final LocalTime now = LocalTime.now();
        return from.equals(now) || to.equals(now)
                || (from.isBefore(now) && to.isAfter(now));
    }
}
