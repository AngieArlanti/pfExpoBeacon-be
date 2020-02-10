package itba.edu.ar.pfexpobeaconbe.api.stats.application;

import itba.edu.ar.pfexpobeaconbe.api.stats.domain.StandVisitHours;
import itba.edu.ar.pfexpobeaconbe.api.stats.domain.StandVisitHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StandVisitHoursService {

    @Autowired
    private StandVisitHoursRepository standVisitHoursRepository;

    public void saveAll(List<StandVisitHours> standVisitHours){
        standVisitHoursRepository.saveAll(standVisitHours);
    }
}
