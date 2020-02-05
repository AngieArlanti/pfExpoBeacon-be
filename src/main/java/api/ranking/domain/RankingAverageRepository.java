package api.ranking.domain;

import api.stand.domain.Stand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingAverageRepository extends JpaRepository<RankingAverage, Stand> { }
