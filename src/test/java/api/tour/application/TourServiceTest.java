package api.tour.application;

import api.stand.application.StandService;
import api.stand.domain.Stand;
import api.stats.application.StandStatics;
import api.stats.application.StatsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class TourServiceTest {

    @TestConfiguration
    static class TourServiceTestConfigutarion {

        @Bean
        public TourService tourService() {
            return new TourService();
        }
    }

    @Autowired
    private TourService tourService;

    @MockBean
    private StandService standService;

    @MockBean
    private StatsService statsService;

    @Test
    public void orderStands_withEmptyStandStatics_isOk() {
        when(statsService.getCurrentStandStatics()).thenReturn(Collections.emptyList());
        final List<Stand> stands = tourService.orderStands();
        assertThat(stands, empty());
    }

    @Test
    public void orderStands_threeStandWithSameRanking() {
        final Stand stand1 = new Stand("1", 5); // cong 5 - hist 10
        final Stand stand2 = new Stand("2", 5); //cong 7 - hist 5
        final Stand stand3 = new Stand("3", 5); //cong 0 - hist 5

        final StandStatics standStatics1 = new StandStatics(stand1, 1, 0.71,0.5); //0,216
        final StandStatics standStatics2 = new StandStatics(stand2, 1, 1, -0.4); //-0,08
        final StandStatics standStatics3 = new StandStatics(stand3, 1, 0, 1); //0.6

        final List<StandStatics> standStatics = new ArrayList<>();
        standStatics.add(standStatics1);
        standStatics.add(standStatics2);
        standStatics.add(standStatics3);

        when(statsService.getCurrentStandStatics()).thenReturn(standStatics);
        final List<Stand> stands = tourService.orderStands();

        assertThat(stands.get(0).getId(), is(stand3.getId()));
        assertThat(stands.get(1).getId(), is(stand1.getId()));
        assertThat(stands.get(2).getId(), is(stand2.getId()));
    }

    @Test
    public void orderStands_onlyRankingStats() {
        final Stand stand1 = new Stand("1", 5); // cong 5 - hist 10
        final Stand stand2 = new Stand("2", 4); //cong 7 - hist 5
        final Stand stand3 = new Stand("3", 3); //cong 0 - hist 5

        final StandStatics standStatics1 = new StandStatics(stand1, 1, 0,0); //0,216
        final StandStatics standStatics2 = new StandStatics(stand2, 0.8, 0, 0); //-0,08
        final StandStatics standStatics3 = new StandStatics(stand3, 0.6, 0, 0); //0.6

        final List<StandStatics> standStatics = new ArrayList<>();
        standStatics.add(standStatics3);
        standStatics.add(standStatics1);
        standStatics.add(standStatics2);

        when(statsService.getCurrentStandStatics()).thenReturn(standStatics);
        final List<Stand> stands = tourService.orderStands();

        assertThat(stands.get(0).getId(), is(stand1.getId()));
        assertThat(stands.get(1).getId(), is(stand2.getId()));
        assertThat(stands.get(2).getId(), is(stand3.getId()));
    }
}
