package api.ranking.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandRankingRepository extends JpaRepository<StandRankingDevice, StandRankingId> {
    List<StandRankingDevice> findBystandId(String standId);
}