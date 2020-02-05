
package api.ranking.application;

import api.ranking.domain.StandRankingDevice;

public class StandRankingMapper {

    StandRankingDevice toModel(final StandRankingDto standRankingDto){
        return new StandRankingDevice(standRankingDto.getStandId(),
                standRankingDto.getDeviceId(), standRankingDto.getRanking());
    }
}