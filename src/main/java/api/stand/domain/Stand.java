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
     * The Stand's description.
     */
    private String description;

    /**
     * The Stand's short description.
     */
    @JsonProperty("short_description")
    @Column(name = "short_description", nullable = false)
    private String shortDescription;

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

    public Stand() {
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getCover() {
        return cover;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public int getRanking() {
        return ranking;
    }
}
