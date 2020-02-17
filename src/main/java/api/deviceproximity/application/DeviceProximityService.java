package api.deviceproximity.application;

import api.deviceproximity.domain.DeviceProximity;
import api.deviceproximity.domain.DeviceProximityRepository;
import api.stand.application.StandService;
import api.stand.domain.Stand;
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

    public List<DeviceProximity> listAllInmmediateStandRegisters(){
        return deviceProximityRepository.findAllInmmediateStandRegisters();
    }

    private void checkValidStand(List<String> immediateStandIds) {
        List<Stand> found = standService.findBy(immediateStandIds);
        Validate.notEmpty(found);
    }
}
