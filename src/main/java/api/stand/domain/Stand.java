package api.stand.domain;

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
     * The Stand's cover icon.
     */
    @Column(name = "cover_url", nullable = false)
    private String coverUrl;

    /**
     * The Stand's icons list.
     */
    @Fetch(FetchMode.JOIN)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="stand_icon_urls", joinColumns=@JoinColumn(name="stand_id"))
    @Column(name = "icon_url", nullable = false)
    private List<String> iconUrls;

    /**
     * The Stand's ranking. It goes from 1 to 5.
     * TODO: (ma 2019-10-19) ranking feature. Now it is a harcoded value, it has to be a calculated popularity
     * TODO: and/or user's rank.
     */
    private int ranking;

    public Stand() {
    }
}
