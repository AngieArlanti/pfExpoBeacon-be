package api.deviceproximity.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceProximityRepository extends JpaRepository<DeviceProximity, String> {
    List<DeviceProximity> findByOrderByDistanceAscUpdateTimeDesc();
}