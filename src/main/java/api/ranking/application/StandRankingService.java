package api.ranking.application;

import api.ranking.domain.RankingAverage;
import api.ranking.domain.StandRankingDevice;
import api.ranking.domain.StandRankingRepository;
import api.stand.application.StandService;
import api.stand.domain.Stand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandRankingService {

    @Autowired
    private StandRankingRepository standRankingRepository;

    @Autowired
    private RankingAverageService rankingAverageService;

    @Autowired
    private StandService standService;

    private StandRankingMapper mapper = new StandRankingMapper();

    public void save(final StandRankingDto standRankingDto) {
        final Stand stand = standService.findBy(standRankingDto.getStandId());
        final StandRankingDevice standRankingByDevice = mapper.toModel(standRankingDto);
        standRankingRepository.save(standRankingByDevice);
        final RankingAverage rankingAverage = calculateRankingCantRates(standRankingDto);
        rankingAverageService.update(rankingAverage);
    }

    public RankingAverage calculateRankingCantRates(StandRankingDto standRankingDto) {
        standService.findBy(standRankingDto.getStandId());
        List<StandRankingDevice> list = standRankingRepository.findBystandId(standRankingDto.getStandId());
        double rankings = list.stream()
                .mapToInt(r -> r.getRanking())
                .sum();
        int cantRates = list.size();
        return new RankingAverage(standService.findBy(standRankingDto.getStandId()).getRankingAverage().getId(), (rankings / cantRates), cantRates);
    }
}