package itba.edu.ar.pfexpobeaconbe.api.stats.application;

import itba.edu.ar.pfexpobeaconbe.api.stats.domain.ExpoHours;
import itba.edu.ar.pfexpobeaconbe.api.stats.domain.ExpoHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpoHoursService {
    @Autowired
    private ExpoHoursRepository expoHoursRepository;

    public List<ExpoHours> findAll(){
        return expoHoursRepository.findAll();
    }
}
