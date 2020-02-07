package itba.edu.ar.pfexpobeaconbe.api.stats.application;

import itba.edu.ar.pfexpobeaconbe.api.stats.domain.DeviceStandsTimeHistory;
import itba.edu.ar.pfexpobeaconbe.api.stats.domain.DeviceStandsTimeHistoryRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
public class DeviceStandsTimeHistoryService {

    @Autowired
    private DeviceStandsTimeHistoryRepository deviceStandsTimeHistoryRepository;

    public void save(final DeviceStandsTimeHistory deviceStandsTimeHistory){
        Validate.notNull(deviceStandsTimeHistory);
        deviceStandsTimeHistoryRepository.save(deviceStandsTimeHistory);
    }

    public void save(final String deviceId, final List<DeviceLocationHistory> list) {
        Validate.notNull(deviceId);
        Validate.notEmpty(list);
        DeviceLocationHistory init = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            DeviceLocationHistory current = list.get(i);
            if (isLastRegister(list.size()-1, i) || deviceWentAway(init, current)) {
                saveDeviceStandTimeHistory(deviceId, init, current);
                init = current;
            }
        }
    }

    private boolean isLastRegister(final int size, final int index) {
        return size == index;
    }

    private boolean deviceWentAway(final DeviceLocationHistory init,
                                   final DeviceLocationHistory current) {
        return !current.getStandId().equals(init.getStandId());
    }

    private void saveDeviceStandTimeHistory(final String deviceId, final DeviceLocationHistory init,
                                            final DeviceLocationHistory current) {
        final double deltaTime = Duration.between(init.getUpdateTime(), current.getUpdateTime()).getSeconds()/60.0;
        if (deltaTime != 0.0) {
            save(new DeviceStandsTimeHistory(deviceId,
                    init.getStandId(),
                    deltaTime));
        }
    }

    public List<DeviceStandsTimeHistory> findAll() {
        return deviceStandsTimeHistoryRepository.findAll();
    }
}
