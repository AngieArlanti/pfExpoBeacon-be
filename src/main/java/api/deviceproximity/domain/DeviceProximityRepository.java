package api.deviceproximity.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceProximityRepository extends JpaRepository<DeviceProximity, String> {

    @Query("SELECT " +
            "new DeviceProximity (deviceId, standId, distance, updateTime)" +
            "FROM DeviceProximity " +
            "WHERE distance < 1.0 " +
            "order by updateTime desc")
    List<DeviceProximity> findAllInmmediateStandRegistersOrderByUpdateTime();

}
