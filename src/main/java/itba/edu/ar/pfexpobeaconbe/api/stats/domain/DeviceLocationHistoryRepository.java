package itba.edu.ar.pfexpobeaconbe.api.stats.domain;

import itba.edu.ar.pfexpobeaconbe.api.stats.application.DeviceLocationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceLocationHistoryRepository extends JpaRepository<DeviceLocationHistory, Long> {
    List<DeviceLocationHistory> findByAverageTimeProcessedFalse();
}
