package itba.edu.ar.pfexpobeaconbe.api.stand.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandRepository extends JpaRepository<Stand, String> {
    List<Stand> findByOrderByRankingAverage_RankingDesc();
}
