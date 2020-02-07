package itba.edu.ar.pfexpobeaconbe.api.deviceproximity.application;

public class NearbyStandDto {

    private String standId;
    private Double distance;

    public NearbyStandDto() {}

    public String getStandId() {
        return standId;
    }

    public Double getDistance() {
        return distance;
    }
}
