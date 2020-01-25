package api.stand.application;

import api.stand.domain.StandRanking;
import api.stand.domain.StandRankingRepository;
import api.stand.domain.StandRanking;
import api.stand.domain.Stand;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StandRankingService {

    @Autowired
    private StandRankingRepository standRankingRepository;

    private StandService standService = new StandService();
    private StandRankingMapper mapper = new StandRankingMapper();

    public void save(final StandRankingDto standRankingDto) {
        checkValidStand(standRankingDto.getStandId());
        StandRanking standRanking = mapper.toModel(standRankingDto);
        standRankingRepository.save(standRanking);
    }

    private void checkValidStand(String standId) {
        Stand found = standService.findBy(standId);
        Validate.notNull(found);
    }
}