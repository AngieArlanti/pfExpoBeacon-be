package api.stats.application;

import api.stats.domain.ExpoHours;
import api.stats.domain.ExpoHoursRepository;
import api.stats.domain.StandVisitHours;
import api.stats.domain.StandVisitHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    @Autowired
    private StandVisitHoursRepository standVisitHoursRepository;
    @Autowired
    private ExpoHoursRepository expoHoursRepository;

    private StandVisitHoursMapper standVisitHoursMapper = new StandVisitHoursMapper();

    /** Returns amount of visits per hour for the given Stand Id.
     *
     * @param standId to calculate amount of visits per hour.
     * @return amount of visits per hour for the given Stand Id.
     */
    List<StandVisitHoursDto> getStandVisitHours(final String standId){
        List<StandVisitHours> standVisitHours = standVisitHoursRepository.findByStandId(standId);
        List<ExpoHours> expoHours = expoHoursRepository.findAll();

        return standVisitHoursMapper.toDto(standVisitHours, expoHours);
    }
}
