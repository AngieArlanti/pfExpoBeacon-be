package api.stats.application;

import api.stats.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StatsService {

    @Autowired
    private StandVisitHoursRepository standVisitHoursRepository;
    @Autowired
    private ExpoHoursRepository expoHoursRepository;

    private StandVisitHoursMapper standVisitHoursMapper = new StandVisitHoursMapper();

    /**
     * TODO jdoc
     * @param standId
     * @return
     */
    List<StandVisitHoursDto> getStandVisitHours(final String standId){
        List<ExpoHours> expoHours = expoHoursRepository.findAll();
        StandVisitHoursId id;
        List<StandVisitHoursDto> standVisitHoursDtos = new ArrayList<>();
        for (ExpoHours expoHour : expoHours) {
            id = new StandVisitHoursId(standId, expoHour.getId());
            //TODO refactor findByStandId!!!
            Optional<StandVisitHours> standVisitHours = standVisitHoursRepository.findById(id);
            standVisitHours.ifPresent(standVisitHours1 ->
                    standVisitHoursDtos.add(standVisitHoursMapper.toDto(standVisitHours1, expoHour)) );
        }
        return standVisitHoursDtos;
    }
}
