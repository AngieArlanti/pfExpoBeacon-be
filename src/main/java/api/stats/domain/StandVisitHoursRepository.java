package api.stats.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandVisitHoursRepository extends JpaRepository<StandVisitHours, StandVisitHoursId> {
    List<StandVisitHours> findByStandId(final String standId);
}