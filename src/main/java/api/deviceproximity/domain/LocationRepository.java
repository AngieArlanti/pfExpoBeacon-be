package api.deviceproximity.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {

    List<Location> findByUpdateTimeBetween(final OffsetDateTime start, final OffsetDateTime end);
}
