package itba.edu.ar.pfexpobeaconbe.api.ranking.domain;

import itba.edu.ar.pfexpobeaconbe.api.stand.domain.Stand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingAverageRepository extends JpaRepository<RankingAverage, Stand> { }
