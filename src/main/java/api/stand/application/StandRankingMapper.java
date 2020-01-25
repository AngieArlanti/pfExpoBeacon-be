
package api.stand.application;

import api.stand.domain.StandRanking;

public class StandRankingMapper {

    StandRanking toModel(final StandRankingDto standRankingDto){
        return new StandRanking(standRankingDto.getStandId(),
                standRankingDto.getDeviceId(), standRankingDto.getRanking());
    }
}