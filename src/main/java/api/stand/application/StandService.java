package api.stand.application;

import api.stand.domain.Stand;
import api.stand.domain.StandRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StandService {

    /** The Stand's Repository. Never null.
     */

    @Autowired
    private StandRepository standRepository;

    /** Returns a Stand with the given id.
     *
     * @param id Stand's id. MUST be the natural id beacon's MAC ADDRESS.
     * @return a Stand.
     */
    @Transactional
    public Stand findBy(final String id) {
        final Optional<Stand> stand = standRepository.findById(id);
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
        final List<Stand> stands = standRepository.findAllById(ids);
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
        final List<Stand> list = standRepository.findByOrderByRankingAverage_RankingDesc();
        return list;
    }
}
