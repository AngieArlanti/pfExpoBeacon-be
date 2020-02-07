package itba.edu.ar.pfexpobeaconbe.api.stand;

import itba.edu.ar.pfexpobeaconbe.api.ranking.domain.RankingAverage;
import itba.edu.ar.pfexpobeaconbe.api.stand.domain.Stand;

import java.util.List;

public final class StandTestBuilder {

    public StandTestBuilder builder() {
        return new StandTestBuilder();
    }

    public static final String ID1 = "1";
    public static final String ID2 = "2";
    public static final String ID3 = "3";
    public static final double RANKING_5 = 5.0;

    private String id;

    /**
     * The Stand's title.
     */
    private String title;

    /**
     * The Stand's short description.
     */
    private String shortDescription;

    /**
     * The Stand's description.
     */
    private String description;

    /**
     * The Stand's cover icon.
     */
    private String cover;

    /**
     * The Stand's picture list.
     */
    private List<String> pictures;

    /**
     * The Stand's ranking. It goes from 1 to 5.
     * */
    private RankingAverage rankingAverage;

    /**
     * Stand's latitude position.
     */
    private double latitude;

    /**
     * Stand's longitude position.
     */
    private double longitude;

    /**
     * Stand's number.
     */
    private int standNumber;

    /**
     * Average time people spend in this Stand.
     * It is measured in hours.
     * It is calculated async.
     */
    private Double averageTime;

    public StandTestBuilder setId(final String id) {
        this.id = id;
        return this;
    }

    public StandTestBuilder setTitle(final String title) {
        this.title = title;
        return this;
    }

    public StandTestBuilder setShortDescription(final String shortDescription) {
        this.shortDescription = shortDescription;
        return this;
    }

    public StandTestBuilder setDescription(final String description) {
        this.description = description;
        return this;
    }

    public StandTestBuilder setCover(final String cover) {
        this.cover = cover;
        return this;
    }

    public StandTestBuilder setPictures(final List<String> pictures) {
        this.pictures = pictures;
        return this;
    }

    public StandTestBuilder setRanking(final double ranking) {
        this.rankingAverage = new RankingAverage();
        rankingAverage.setRanking(ranking);
        return this;
    }

    public StandTestBuilder setLatitude(final double latitude) {
        this.latitude = latitude;
        return this;
    }

    public StandTestBuilder setLongitude(final double longitude) {
        this.longitude = longitude;
        return this;
    }

    public StandTestBuilder setStandNumber(final int standNumber) {
        this.standNumber = standNumber;
        return this;
    }

    public StandTestBuilder setAverageTime(final Double averageTime) {
        this.averageTime = averageTime;
        return this;
    }

    public Stand build() {
        return new Stand(id, title, shortDescription, description, cover, pictures,
            rankingAverage, latitude, longitude, averageTime);
    }
}
