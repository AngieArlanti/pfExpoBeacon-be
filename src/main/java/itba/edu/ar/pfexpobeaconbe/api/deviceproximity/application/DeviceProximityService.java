package itba.edu.ar.pfexpobeaconbe.api.deviceproximity.application;

import itba.edu.ar.pfexpobeaconbe.api.deviceproximity.domain.DeviceProximity;
import itba.edu.ar.pfexpobeaconbe.api.deviceproximity.domain.DeviceProximityRepository;
import itba.edu.ar.pfexpobeaconbe.api.stand.application.StandService;
import itba.edu.ar.pfexpobeaconbe.api.stand.domain.Stand;
import itba.edu.ar.pfexpobeaconbe.api.stats.application.ExpoHoursService;
import itba.edu.ar.pfexpobeaconbe.api.stats.domain.ExpoHours;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class DeviceProximityService {

    public static final long INVALID_EXPO_HOUR_INTERVAL = -1L;

    @Autowired
    private DeviceProximityRepository deviceProximityRepository;

    @Autowired
    private ExpoHoursService expoHoursService;

    @Autowired
    private StandService standService;

    private DeviceProximityMapper mapper = new DeviceProximityMapper();

    public void save(final DeviceProximityDto deviceProximityDto) {
        checkValidStand(deviceProximityDto.getNearbyStandIds());
        List<DeviceProximity> deviceProximity = mapper.toModel(deviceProximityDto);
        final List<ExpoHours> expoHours = expoHoursService.findAll();
        deviceProximity.forEach(dp -> {
            setHourInterval(dp, expoHours);
            if(hasValidExpoHourInterval(dp)){
                deviceProximityRepository.save(dp);
            }
        });
    }

    private boolean hasValidExpoHourInterval(DeviceProximity dp) {
        return !dp.getExpoHourIntervalId().equals(INVALID_EXPO_HOUR_INTERVAL);
    }

    private void setHourInterval(final DeviceProximity deviceProximity, final List<ExpoHours> expoHours) {
        final Long hourIntervalId = getHourInterval(deviceProximity.getUpdateTime(), expoHours);
        deviceProximity.setExpoHourIntervalId(hourIntervalId);
    }

    private Long getHourInterval(final OffsetDateTime updateTime, final List<ExpoHours> expoHours) {
        final LocalTime time = updateTime.toLocalTime();
        for (ExpoHours expoHour: expoHours) {
            if(belongsToExpoHourInterval(time, expoHour)){
                return expoHour.getId();
            }
        }
        return INVALID_EXPO_HOUR_INTERVAL;
    }

    private boolean belongsToExpoHourInterval(final LocalTime time, final ExpoHours expoHour) {
        return time.equals(expoHour.getFrom()) ||
                (time.isAfter(expoHour.getFrom()) && time.isBefore(expoHour.getTo()));
    }

    public List<DeviceProximity> listAll(){
        return deviceProximityRepository.findAll();
    }

    private void checkValidStand(final List<String> immediateStandIds) {
        List<Stand> found = standService.findBy(immediateStandIds);
        Validate.notEmpty(found);
    }
}
