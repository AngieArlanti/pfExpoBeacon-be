package api.stand.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StandRankingRepository extends JpaRepository<StandRanking, StandRankingId> {}