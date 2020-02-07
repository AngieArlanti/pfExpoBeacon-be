package itba.edu.ar.pfexpobeaconbe.api.ranking.application;

import itba.edu.ar.pfexpobeaconbe.api.ranking.domain.RankingAverage;
import itba.edu.ar.pfexpobeaconbe.api.ranking.domain.RankingAverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankingAverageService {

    @Autowired
    private RankingAverageRepository rankingAverageRepository;


    public void update(RankingAverage rankingAverage) {
        rankingAverageRepository.save(rankingAverage);
    }
}
