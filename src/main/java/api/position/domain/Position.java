package api.position.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Position {

    @Id
    private int id;

    private double longitude;

    private double latitude;

    public Position() {
    }

    public Position(final double longitude, final double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}