package itba.edu.ar.pfexpobeaconbe.api.stand.domain;

import itba.edu.ar.pfexpobeaconbe.api.ranking.domain.RankingAverage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

/**
 * A Stand.
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Stand {

    /**
     * Stand's Id. It MUST BE the Beacons' unique mac address.
     */
    @Id
    private String id;

    /**
     * The Stand's title.
     */
    private String title;

    /**
     * The Stand's short description.
     */
    @Column(name = "short_description", nullable = false)
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
    @Fetch(FetchMode.JOIN)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="stand_pictures", joinColumns=@JoinColumn(name="stand_id"))
    @Column(name = "picture", nullable = false)
    private List<String> pictures;

    /**
     * The Stand's ranking. It goes from 1 to 5.
     */
    @OneToOne
    @JoinColumn(name = "ranking_average_id", referencedColumnName = "id")
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
    @Column(name = "stand_number", nullable = false)
    @GeneratedValue
    private int standNumber;

    /**
     * Average time people spend in this Stand.
     * It is measured in hours.
     * It is calculated async.
     */
    @Column(name = "average_time")
    private Double averageTime;

    /** Empty constructor. For Hibernate purposes.
     */
    public Stand() {
    }

    public Stand(final String id, final String title, final String shortDescription,
                 final String description, final String cover, final List<String> pictures,
                 final RankingAverage rankingAverage, final double latitude,
                 final double longitude, final Double averageTime) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.cover = cover;
        this.pictures = pictures;
        this.rankingAverage = rankingAverage;
        this.latitude = latitude;
        this.longitude = longitude;
        this.averageTime = averageTime;
    }

    /** Returns the Stand's id.
     *
     * @return the Stand's id.
     */
    public String getId() {
        return id;
    }

    /** Returns the Stand's title.
     *
     * @return the Stand's title.
     */
    public String getTitle() {
        return title;
    }

    /** Returns the Stand's description.
     *
     * @return the Stand's description.
     */
    public String getDescription() {
        return description;
    }

    /** Returns the Stand's shortDescription.
     *
     * @return the Stand's shortDescription.
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /** Returns the Stand's cover.
     *
     * @return the Stand's cover.
     */
    public String getCover() {
        return cover;
    }

    /** Returns the Stand's pictures.
     *
     * @return the Stand's pictures' list.
     */
    public List<String> getPictures() {
        return pictures;
    }

    public RankingAverage getRankingAverage() {
        return rankingAverage;
    }

    @JsonIgnore
    public double getRanking(){
        return rankingAverage != null ? rankingAverage.getRanking() : 0;
    }

    /** Returns the Stand's latitude position.
     *
     * @return the Stand's latitude position.
     */
    public double getLatitude() {
        return latitude;
    }

    /** Returns the Stand's longitude position.
     *
     * @return the Stand's longitude position.
     */
    public double getLongitude() {
        return longitude;
    }

    /** Returns the Stand's number.
     *
     * @return the Stand's number.
     */
    public int getStandNumber() {
        return standNumber;
    }

    /** Returns average time people spend in this Stand.
     * It is measured in minutes.
     *
     * @return the average time people spend in this Stand.
     */
    public Double getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(final double averageTime) {
        this.averageTime = averageTime;
    }
}
