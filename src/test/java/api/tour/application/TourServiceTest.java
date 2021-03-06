package api.tour.application;

import api.position.domain.Position;
import api.stand.StandTestBuilder;
import api.stand.application.StandService;
import api.stand.domain.Stand;
import api.stats.application.StandStatics;
import api.stats.application.StatsService;
import api.stats.application.TimeTourStandStatics;
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
    static class TourServiceTestConfiguration {
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
        final List<Stand> stands = tourService.sortStandsByTimeTourStats(new Position(), Collections.emptyList());
        assertThat(stands, empty());
    }

    @Test
    public void orderStands_threeStandWithSameRanking() {
        final StandTestBuilder builder = new StandTestBuilder();
        // cong 5 - hist 10
        final Stand stand1 = builder
                .setId("1")
                .setRanking(5.0)
                .setLatitude(0.3)
                .setLongitude(0.3)
                .build();

        //cong 7 - hist 5
        final Stand stand2 = builder
                .setId("2")
                .setRanking(5.0)
                .setLatitude(0.3)
                .setLongitude(0.3)
                .build();

        //cong 0 - hist 5
        final Stand stand3 = builder
                .setId("3")
                .setRanking(5.0)
                .setLatitude(0.3)
                .setLongitude(0.3)
                .build();

        final StandStatics standStatics1 = new TimeTourStandStatics(stand1, 1, 0.71, 0.5, 0.1); //0,216
        final StandStatics standStatics2 = new TimeTourStandStatics(stand2, 1, 1, -0.4, 0.1); //-0,08
        final StandStatics standStatics3 = new TimeTourStandStatics(stand3, 1, 0, 1, 0.1); //0.6

        final List<Stand> stands = new ArrayList<>();
        stands.add(stand1);
        stands.add(stand2);
        stands.add(stand3);

        final List<StandStatics> standStatics = new ArrayList<>();
        standStatics.add(standStatics1);
        standStatics.add(standStatics2);
        standStatics.add(standStatics3);

        final Position position = new Position(0.33, 0.33);

        when(statsService.getTimeTourStats(position, stands)).thenReturn(standStatics);
        final List<Stand> sortedStands = tourService.sortStandsByTimeTourStats(position, stands);

        assertThat(sortedStands.get(0).getId(), is(stand3.getId()));
        assertThat(sortedStands.get(1).getId(), is(stand1.getId()));
        assertThat(sortedStands.get(2).getId(), is(stand2.getId()));
    }

    @Test
    public void orderStands_onlyRankingStats() {
        final StandTestBuilder builder = new StandTestBuilder();
        // cong 5 - hist 10
        final Stand stand1 = builder
                .setId("1")
                .setRanking(5.0)
                .setLatitude(0.3)
                .setLongitude(0.3)
                .build();

        //cong 7 - hist 5
        final Stand stand2 = builder
                .setId("2")
                .setRanking(5.0)
                .setLatitude(0.3)
                .setLongitude(0.3)
                .build();

        //cong 0 - hist 5
        final Stand stand3 = builder
                .setId("3")
                .setRanking(5.0)
                .setLatitude(0.3)
                .setLongitude(0.3)
                .build();

        final StandStatics standStatics1 = new TimeTourStandStatics(stand1, 1, 0, 0, 0.33); //0,216
        final StandStatics standStatics2 = new TimeTourStandStatics(stand2, 0.8, 0, 0, 0.33); //-0,08
        final StandStatics standStatics3 = new TimeTourStandStatics(stand3, 0.6, 0, 0, 0.33); //0.6

        final List<Stand> stands = new ArrayList<>();
        stands.add(stand1);
        stands.add(stand2);
        stands.add(stand3);

        final List<StandStatics> standStatics = new ArrayList<>();
        standStatics.add(standStatics3);
        standStatics.add(standStatics1);
        standStatics.add(standStatics2);

        final Position position = new Position(0.33, 0.33);

        when(statsService.getTimeTourStats(position, stands)).thenReturn(standStatics);
        final List<Stand> sortedStands = tourService.sortStandsByTimeTourStats(position, stands);

        assertThat(sortedStands.get(0).getId(), is(stand1.getId()));
        assertThat(sortedStands.get(1).getId(), is(stand2.getId()));
        assertThat(sortedStands.get(2).getId(), is(stand3.getId()));
    }

    @Test
    public void orderStands_threeStandWithDifferentPositions() {
        final StandTestBuilder builder = new StandTestBuilder();
        // cong 5 - hist 10
        final Stand stand1 = builder
                .setId("1")
                .setRanking(5.0)
                .setLatitude(0.15)
                .setLongitude(0.15)
                .build();

        //cong 7 - hist 5
        final Stand stand2 = builder
                .setId("2")
                .setRanking(5.0)
                .setLatitude(0.2)
                .setLongitude(0.2)
                .build();

        //cong 0 - hist 5
        final Stand stand3 = builder
                .setId("3")
                .setRanking(5.0)
                .setLatitude(0.3)
                .setLongitude(0.3)
                .build();

        final StandStatics standStatics1 = new TimeTourStandStatics(stand1, 1, 1, 1, 0.1);
        final StandStatics standStatics2 = new TimeTourStandStatics(stand2, 1, 1, 1, 0.2);
        final StandStatics standStatics3 = new TimeTourStandStatics(stand3, 1, 1, 1, 0.3);

        final List<Stand> stands = new ArrayList<>();
        stands.add(stand2);
        stands.add(stand1);
        stands.add(stand3);

        final List<StandStatics> standStatics = new ArrayList<>();
        standStatics.add(standStatics1);
        standStatics.add(standStatics2);
        standStatics.add(standStatics3);

        final Position position = new Position(0.1, 0.1);

        when(statsService.getTimeTourStats(position, stands)).thenReturn(standStatics);
        final List<Stand> sortedStands = tourService.sortStandsByTimeTourStats(position, stands);

        assertThat(sortedStands.get(0).getId(), is(stand1.getId()));
        assertThat(sortedStands.get(1).getId(), is(stand2.getId()));
        assertThat(sortedStands.get(2).getId(), is(stand3.getId()));
    }

    @Test
    public void sortStands() {
        final StandTestBuilder builder = new StandTestBuilder();
        // cong 5 - hist 10
        final Stand stand1 = builder
                .setId("1")
                .setRanking(5.0)
                .setLatitude(0.1)
                .setLongitude(0.1)
                .build();

        //cong 7 - hist 5
        final Stand stand2 = builder
                .setId("2")
                .setRanking(5.0)
                .setLatitude(0.2)
                .setLongitude(0.2)
                .build();

        //cong 0 - hist 5
        final Stand stand3 = builder
                .setId("3")
                .setRanking(5.0)
                .setLatitude(0.3)
                .setLongitude(0.3)
                .build();

        final List<Stand> stands = new ArrayList<>();
        stands.add(stand1);
        stands.add(stand2);
        stands.add(stand3);

        final Stand startStand = builder
                .setId("4")
                .setRanking(5.0)
                .setLatitude(0.35)
                .setLongitude(0.35)
                .build();


        List<Stand> sortedStands = tourService.sortStandsByDistances(startStand, stands);

        assertThat(sortedStands.get(0).getId(), is(stand3.getId()));
        assertThat(sortedStands.get(1).getId(), is(stand2.getId()));
        assertThat(sortedStands.get(2).getId(), is(stand1.getId()));
    }
}
