package api.ranking.application;

import api.ranking.domain.RankingAverage;
import api.ranking.domain.RankingAverageRepository;
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
