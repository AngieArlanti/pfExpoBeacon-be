package itba.edu.ar.pfexpobeaconbe.api.stats.domain;

import itba.edu.ar.pfexpobeaconbe.api.stats.application.DeviceLocationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeviceLocationHistoryRepository extends JpaRepository<DeviceLocationHistory, Long> {
    List<DeviceLocationHistory> findByAverageTimeProcessedFalse();
    List<DeviceLocationHistory> findByHistogramProcessedFalse();

    @Query("SELECT " +
            "new StandVisitHours (standId, expoHoursId, count(distinct deviceId)) " +
            "FROM DeviceLocationHistory " +
            "WHERE histogramProcessed = false AND distance < 1.0 " +
            "GROUP BY standId, expoHoursId")
    List<StandVisitHours> findStandVisitHours();
}
