package api.stand.application;

import api.stand.domain.*;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.OffsetTime.now;
import static java.util.stream.Collectors.groupingBy;

@Service
public class StandService {

    /** The Stand's Repository. Never null.
     */
    //private StandRepository standRepository = new StandRepository();

    @Autowired
    private StandRepository stand2Repository;

    @Autowired
    private DeviceProximityService deviceProximityService;

    /** Returns a Stand with the given id.
     *
     * @param id Stand's id. MUST be the natural id beacon's MAC ADDRESS.
     * @return a Stand.
     */
    @Transactional
    public Stand findBy(final String id) {
        final Optional<Stand> stand = stand2Repository.findById(id);
        Validate.notNull(stand,"Stand " + id + " not found");
        return stand.get();
    }

    /** Returns a Stand's List using the given Stands' ids.
     *
     * @param ids the Stands's ids.
     * @return a Stand's list.
     * */
    @Transactional
    public  List<Stand>  findBy(final List<String> ids) {
        final List<Stand> stands = stand2Repository.findAllById(ids);
        Validate.notEmpty(stands,"Stands " + ids + " not found");
        Validate.isTrue(stands.size() == ids.size(), "Stands not found");
        return stands;
    }

    /** Returns all the available Stands ordered by ranking.
     *
     * @return a Stand's list ordered by ranking.
     */
    @Transactional
    public List<Stand> listOrderedByRanking() {
        final List<Stand> list = stand2Repository.findByOrderByRankingAverage_RankingDesc();
        return list;
    }

    List<Stand> findSuggestedTourByCongestion() {
        List<DeviceProximity> deviceProximityList = deviceProximityService.listAll();
        Map<String, Long> congestionMap =  deviceProximityList.stream().filter(this::isUpdated)
                .collect(groupingBy(DeviceProximity::getImmediateStandId, Collectors.counting()));

        List<Stand> stands = findBy(congestionMap.keySet().stream().collect(Collectors.toList()));

        Map<Stand, Long> standCongestion = new HashMap<>();

        for (Stand stand: stands) {
            Long congestion = congestionMap.get(stand.getId());
            standCongestion.put(stand, congestion);
        }

        List<Stand> orderedSuggestedStandIds = standCongestion.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return orderedSuggestedStandIds;
    }

    private boolean isUpdated(DeviceProximity deviceProximity) {
        return Duration.between(now(), deviceProximity.getUpdateTime()).getSeconds() <= 600;
    }

    /*public void update(String standId, StandRankingRates rankingRates) {
        deviceProximityService.checkValidStand(standId);
        standRepository.update(standId, rankingRates);
    }*/
}
