package api.stats.application;

import api.deviceproximity.application.DeviceProximityService;
import api.stand.StandTestBuilder;
import api.stand.application.StandService;
import api.stand.domain.Stand;
import api.stats.domain.ExpoHoursRepository;
import api.stats.domain.StandVisitHoursRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
public class StatsServiceTest {

    @TestConfiguration
    static class StatsServiceTestConfiguration {

        @Bean
        public StatsService statsService() {
            return new StatsService();
        }
    }

    @Autowired
    private StatsService statsService;

    @MockBean
    private StandService standService;

    @MockBean
    private StandVisitHoursRepository standVisitHoursRepository;

    @MockBean
    private ExpoHoursRepository expoHoursRepository;

    @MockBean
    private DeviceProximityService deviceProximityService;

    @Test
    public void getStandStatics_generateStandStaticsOk() {
        final StandTestBuilder builder = new StandTestBuilder();
        // cong 5 - hist 10
        final Stand stand1 = builder
                .setId("1")
                .setRanking(5.0)
                .build();

        //cong 7 - hist 5
        final Stand stand2 = builder
                .setId("2")
                .setRanking(5.0)
                .build();

        //cong 0 - hist 5
        final Stand stand3 = builder
                .setId("3")
                .setRanking(5.0)
                .build();

        final List<Stand> stands = new ArrayList<>();
        stands.add(stand1);
        stands.add(stand2);
        stands.add(stand3);

        final Map<String, Long> currentCongestion = new HashMap<>();
        currentCongestion.put("1", 5L);
        currentCongestion.put("2", 7L);
        currentCongestion.put("3", 0L);

        final Map<String, Long> historicCongestion = new HashMap<>();
        historicCongestion.put("1", 10L);
        historicCongestion.put("2", 5L);
        historicCongestion.put("3", 5L);

        final List<StandStatics> standStatics = statsService.getStandStatics(stands, currentCongestion,
                historicCongestion);

        assertThat(standStatics.get(0).getStand().getId(), is("1"));
        assertThat(standStatics.get(0).getNormalizedRanking(), is(1.0));
        assertThat(standStatics.get(0).getNormalizedCurrentCongestion(), is(0.7142857142857143));
        assertThat(standStatics.get(0).getNormalizedOpportunity(), is(0.5));

        assertThat(standStatics.get(1).getStand().getId(), is("2"));
        assertThat(standStatics.get(1).getNormalizedRanking(), is(1.0));
        assertThat(standStatics.get(1).getNormalizedCurrentCongestion(), is(1.0));
        assertThat(standStatics.get(1).getNormalizedOpportunity(), is(-0.4));

        assertThat(standStatics.get(2).getStand().getId(), is("3"));
        assertThat(standStatics.get(2).getNormalizedRanking(), is(1.0));
        assertThat(standStatics.get(2).getNormalizedCurrentCongestion(), is(0.0));
        assertThat(standStatics.get(2).getNormalizedOpportunity(), is(1.0));
    }
}
