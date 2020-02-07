package itba.edu.ar.pfexpobeaconbe.api.deviceproximity.application;

import itba.edu.ar.pfexpobeaconbe.api.deviceproximity.domain.DeviceProximity;
import itba.edu.ar.pfexpobeaconbe.api.deviceproximity.domain.DeviceProximityRepository;
import itba.edu.ar.pfexpobeaconbe.api.stand.application.StandService;
import itba.edu.ar.pfexpobeaconbe.api.stand.domain.Stand;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceProximityService {

    @Autowired
    private DeviceProximityRepository deviceProximityRepository;

    @Autowired
    private StandService standService;
    private DeviceProximityMapper mapper = new DeviceProximityMapper();

    public void save(final DeviceProximityDto deviceProximityDto) {
        checkValidStand(deviceProximityDto.getNearbyStandIds());
        List<DeviceProximity> deviceProximity = mapper.toModel(deviceProximityDto);
        deviceProximityRepository.saveAll(deviceProximity);
    }

    public List<DeviceProximity> listAll(){
        return deviceProximityRepository.findAll();
    }

    private void checkValidStand(List<String> immediateStandIds) {
        List<Stand> found = standService.findBy(immediateStandIds);
        Validate.notEmpty(found);
    }
}
