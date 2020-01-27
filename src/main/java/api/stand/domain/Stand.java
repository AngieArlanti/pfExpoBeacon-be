package api.stand.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("short_description")
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
     * TODO: (ma 2019-10-19) ranking feature. Now it is a harcoded value, it has to be a calculated popularity
     * TODO: and/or user's rank.
     */
    private int ranking;

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
    @JsonProperty("stand_number")
    @Column(name = "stand_number", nullable = false)
    @GeneratedValue
    private int standNumber;

    /** Empty constructor. For Hibernate purposes.
     */
    public Stand() {
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

    /** Returns the Stand's ranking.
     *
     * @return the Stand's ranking.
     */
    public int getRanking() {
        return ranking;
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


}
