package itba.edu.ar.pfexpobeaconbe.api.stats.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TourVisitsRepository extends JpaRepository<TourVisits, String> {
    List<TourVisits> findByOrderByVisitsDesc();
}
