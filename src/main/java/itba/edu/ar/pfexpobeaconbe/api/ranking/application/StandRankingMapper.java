
package itba.edu.ar.pfexpobeaconbe.api.ranking.application;

import itba.edu.ar.pfexpobeaconbe.api.ranking.domain.StandRankingDevice;

public class StandRankingMapper {

    StandRankingDevice toModel(final StandRankingDto standRankingDto){
        return new StandRankingDevice(standRankingDto.getStandId(),
                standRankingDto.getDeviceId(), standRankingDto.getRanking());
    }
}