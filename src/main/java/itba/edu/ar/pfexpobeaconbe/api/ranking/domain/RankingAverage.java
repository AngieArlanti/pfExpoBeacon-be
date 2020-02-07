package itba.edu.ar.pfexpobeaconbe.api.ranking.domain;

import itba.edu.ar.pfexpobeaconbe.api.stand.domain.Stand;

import javax.persistence.*;

@Entity
@Table(name = "ranking_average")
public class RankingAverage {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @OneToOne(mappedBy = "rankingAverage")
    private Stand stand;

    private double ranking;

    private int cantRates;

    public RankingAverage() {
    }

    public RankingAverage(int id, double ranking, int cantRates) {
        this.id = id;
        this.ranking = ranking;
        this.cantRates = cantRates;
    }

    public int getId() {
        return id;
    }

    public double getRanking() {
        return ranking;
    }

    public int getCantRates() {
        return cantRates;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public void setRanking(double ranking) {
        this.ranking = ranking;
    }

    public void setCantRates(int cantRates) {
        this.cantRates = cantRates;
    }
}
