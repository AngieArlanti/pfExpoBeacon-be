
package api.stand.application;

import api.stand.domain.StandRankingDevice;

public class StandRankingMapper {

    StandRankingDevice toModel(final StandRankingDto standRankingDto){
        return new StandRankingDevice(standRankingDto.getStandId(),
                standRankingDto.getDeviceId(), standRankingDto.getRanking());
    }
}