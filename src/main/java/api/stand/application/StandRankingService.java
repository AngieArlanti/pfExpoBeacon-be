package api.stand.application;

import api.stand.domain.StandRanking;
import api.stand.domain.StandRankingRepository;
import api.stand.domain.StandRanking;
import api.stand.domain.Stand;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandRankingService {

    @Autowired
    private StandRankingRepository standRankingRepository;

    @Autowired
    private DeviceProximityService deviceProximityService;

    private StandService standService = new StandService();
    private StandRankingMapper mapper = new StandRankingMapper();

    public void save(final StandRankingDto standRankingDto) {
        deviceProximityService.checkValidStand(standRankingDto.getStandId());
        StandRanking standRanking = mapper.toModel(standRankingDto);
        standRankingRepository.save(standRanking);
    }

    public double calculateRanking(StandRankingDto standRankingDto) {
        deviceProximityService.checkValidStand(standRankingDto.getStandId());
        List<StandRanking> list = standRankingRepository.findAll();
        double rankings = 0;
        int elem = 0;
        for (StandRanking standRanking : list) {
            if(standRanking.getStandId().equals(standRankingDto.getStandId())){
                rankings += standRanking.getRanking();
                elem++;
            }
        }
        return (rankings / elem);
    }
}