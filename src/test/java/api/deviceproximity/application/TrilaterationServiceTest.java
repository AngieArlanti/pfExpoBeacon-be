package api.deviceproximity.application;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.linear.RealVector;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static api.deviceproximity.application.SphericalMercatorProjection.ecefToLocation;
import static api.deviceproximity.application.SphericalMercatorProjection.locationToEcef;
import static java.lang.Math.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@Ignore
public class TrilaterationServiceTest {

    private TrilaterationService trilaterationService = new TrilaterationService();

    @Test
    public void getLocation() {
        final List<Point> points = new ArrayList<>();
        points.add(new Point(5.0, -6.0, 8.06));
        points.add(new Point(13.0, -15.0, 13.97));
        points.add(new Point(21.0, -3.0, 23.32));

        final Point point = trilaterationService.getLocation(points);
        assertThat(point.getRoundingHalfUpLatitude(), is(-30.3));
        assertThat(point.getRoundingHalfUpLongitude(), is(-9.13));
    }

    @Test
    public void getPointWithTrilaterationLibrary() {
        double[][] positions = new double[3][2];
        double[] distances = new double[3];

        positions[0][0] = 0;
        positions[0][1] = 0;
        positions[1][0] = 0;
        positions[1][1] = 2;
        positions[2][0] = 4;
        positions[2][1] = 0;

        distances[0]=Math.sqrt(5);
        distances[1]=Math.sqrt(5);
        distances[2]=Math.sqrt(5);


        RealVector ret = trilaterationService.getPointWithTrilaterationLibrary(positions, distances);

        assertThat(ret.toString(), is("{2; 1}"));
    }

    @Test
    public void getPointWithTrilaterationLibrary_withLatLong() {
        double[][] positions = new double[3][3];
        double[] distances = new double[3];

        double[] xyz1 = convertSphericalToCartesian(-34.519400, -58.509615);
        double[] xyz2 = convertSphericalToCartesian(-34.518744, -58.510115);
        double[] xyz3 = convertSphericalToCartesian(-34.518933, -58.508761);

        positions[0][0] = xyz1[0];
        positions[0][1] = xyz1[1];
        positions[0][2] = xyz1[2];

        positions[1][0] = xyz2[0];
        positions[1][1] = xyz2[1];
        positions[1][2] = xyz2[2];

        positions[2][0] = xyz3[0];
        positions[2][1] = xyz3[1];
        positions[2][2] = xyz3[2];

        distances[0]= 0.063;
        distances[1]= 0.064;
        distances[2]= 0.059;

        RealVector ret = trilaterationService.getPointWithTrilaterationLibrary(positions, distances);

        Vector3D v = new Vector3D(ret.toArray()[0], ret.toArray()[1], ret.toArray()[2]);

        double[] latlon = convertCartesianToGeodesic(v);

        System.out.println("lat: "+ latlon[0] + " lon: " + latlon[1]);

        //assertThat(latLong, is("[<-34.51879825710997>, <-58.509383614314544>]"));
    }

    @Test
    public void getPointWithTrilaterationLibrary_andLocationToEcef() {
        double[][] positions = new double[3][3];
        double[] distances = new double[3];

        double[] xyz1 = locationToEcef(new double[] {-34.519400, -58.509615, 0});
        double[] xyz2 = locationToEcef(new double[] {-34.518744, -58.510115, 0});
        double[] xyz3 = locationToEcef(new double[] {-34.518933, -58.508761, 0});

        positions[0][0] = xyz1[0];
        positions[0][1] = xyz1[1];
        positions[0][2] = xyz1[2];

        positions[1][0] = xyz2[0];
        positions[1][1] = xyz2[1];
        positions[1][2] = xyz2[2];

        positions[2][0] = xyz3[0];
        positions[2][1] = xyz3[1];
        positions[2][2] = xyz3[2];

        distances[0]= 0.063;
        distances[1]= 0.064;
        distances[2]= 0.059;

        RealVector ret = trilaterationService.getPointWithTrilaterationLibrary(positions, distances);

        Vector3D v = new Vector3D(ret.toArray()[0], ret.toArray()[1], ret.toArray()[2]);

        Vector3D latlon = ecefToLocation(new double[] {v.getX(), v.getY(), v.getZ()});

        System.out.println("lat: "+ latlon.getX() + " lon: " + latlon.getY() + " h: " + latlon.getZ());

        //assertThat(latLong, is("[<-34.51879825710997>, <-58.509383614314544>]"));
    }

    //https://gis.stackexchange.com/questions/66/trilateration-using-3-latitude-longitude-points-and-3-distances
    @Test
    public void getPointTrilateration3D_exampleFromWeb() {
        double[] distances = new double[3];

        Vector3D P1 = convertGeodesicToCartesian(37.418436, -121.963477);
        Vector3D P2 = convertGeodesicToCartesian(37.417243, -121.961889);
        Vector3D P3 = convertGeodesicToCartesian(37.418692, -121.960194);

        distances[0]= 0.265710701754;
        distances[1]= 0.234592423446;
        distances[2]= 0.0548954278262;

        assertThat(trilateration3D(Arrays.asList(P1, P2, P3), distances), is(new double[] {37.419102374, -121.960579208}));
    }

    @Test
    public void getPointTrilateration3D() {
        double[] distances = new double[3];

        Vector3D P1 = convertGeodesicToCartesian(-34.519400, -58.509615);
        Vector3D P2 = convertGeodesicToCartesian(-34.518744, -58.510115);
        Vector3D P3 = convertGeodesicToCartesian(-34.518933, -58.508761);

        distances[0]= 0.063;
        distances[1]= 0.064;
        distances[2]= 0.059;

        assertThat(trilateration3D(Arrays.asList(P1, P2, P3), distances), is(new double[] {37.419102374, -121.960579208}));
    }

   /* @Test
    public void getPointTrilateration3D_library() {
        double[] distances = new double[3];

        Vector3D P1 = convertGeodesicToCartesian(-34.519400, -58.509615);
        Vector3D P2 = convertGeodesicToCartesian(-34.518744, -58.510115);
        Vector3D P3 = convertGeodesicToCartesian(-34.518933, -58.508761);

        distances[0]= 0.063;
        distances[1]= 0.064;
        distances[2]= 0.059;



        assertThat(, is(new double[] {37.419102374, -121.960579208}));
    }*/

    private double[] convertSphericalToCartesian(double latitude, double longitude)
    {
        long earthRadius = 6367; //radius in km
        double lat = Math.toRadians(latitude);
        double lon = Math.toRadians(longitude);
        double x = earthRadius * Math.cos(lat)*Math.cos(lon);
        double y = earthRadius * Math.cos(lat)*Math.sin(lon);
        double z = earthRadius * Math.sin(lat);
        return new double[] {x, y, z};
    }

    private double[] convertCartesianToSpherical(double[] cartesian)
    {
        double x = cartesian[0];
        double y = cartesian[1];
        double z = cartesian[2];
        double r = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
        double lat = Math.toDegrees(Math.asin(z/r));
        double lon = Math.toDegrees(Math.atan2(y, x));

        return new double[] {lat, lon};
    }

    /*private double[] trilateration3D_deprecate(final List<Vector3d> positions, double[] distances) {

        Vector3d P1 = positions.get(0);
        Vector3d P2 = positions.get(1);
        Vector3d P3 = positions.get(2);

        Vector3d ex = (P2.sub(P1)).div(euclideanNorm(P2.sub(P1)));
        double i = ex.dot(P3.sub(P1));
        Vector3d P1subP3i = P3.sub(P1).sub(ex.mul(i));

        Vector3d ey = P1subP3i.div(euclideanNorm(P1subP3i));
        Vector3d ez = ex.cross(ey);

        double d = euclideanNorm(P2.sub(P1));
        double j = ey.dot(P3.sub(P1));

        double x = (pow(distances[0],2) - pow(distances[1],2) + pow(d,2))/(2*d);
        double y = ((pow(distances[0],2) - pow(distances[2],2) + pow(i,2) + pow(j,2)) - (2*i*x)) / (2*j);
        double z = sqrt((pow(distances[0],2) - pow(x,2) - pow(y,2)));

        Vector3d triPoint = P1.add(ex.mul(x)).add(ey.mul(y)).add(ez.mul(z));

        return convertCartesianToGeodesic(triPoint);
    }*/

    private double[] trilateration3D(final List<Vector3D> positions, double[] distances) {
        Vector3D P1 = positions.get(0);
        Vector3D P2 = positions.get(1);
        Vector3D P3 = positions.get(2);

        Vector3D ex = div((P2.subtract(P1)), (P2.subtract(P1).getNorm()));
        double i = ex.dotProduct(P3.subtract(P1));
        Vector3D P1subP3i = P3.subtract(P1).subtract(ex.scalarMultiply(i));

        Vector3D ey = div(P1subP3i, P1subP3i.getNorm());
        Vector3D ez = ex.crossProduct(ey);

        double d = P2.subtract(P1).getNorm();
        double j = ey.dotProduct(P3.subtract(P1));

        double x = (pow(distances[0],2) - pow(distances[1],2) + pow(d,2))/(2*d);
        double y = ((pow(distances[0],2) - pow(distances[2],2) + pow(i,2) + pow(j,2)) - (2*i*x)) / (2*j);
        double z = sqrt((pow(distances[0],2) - pow(x,2) - pow(y,2)));

        Vector3D triPoint = P1.add(ex.scalarMultiply(x)).add(ey.scalarMultiply(y)).add(ez.scalarMultiply(z));

        return convertCartesianToGeodesic(triPoint);
    }

    private Vector3D div(Vector3D v, double scalar) {
        return new Vector3D(v.getX()/scalar, v.getY()/scalar, v.getZ()/scalar);
    }

    private Vector3D convertGeodesicToCartesian(double latitude, double longitude)
    {
        long earthRadius = 6371; //radius in km
        double lat = Math.toRadians(latitude);
        double lon = Math.toRadians(longitude);
        double x = earthRadius * Math.cos(lat)*Math.cos(lon);
        double y = earthRadius * Math.cos(lat)*Math.sin(lon);
        double z = earthRadius * Math.sin(lat);

        return new Vector3D(x, y, z);
    }

    /**
     * #convert back to lat/long from ECEF
     * #convert to degrees
     * @param v
     * @return
     */
    private double[] convertCartesianToGeodesic(final Vector3D v)
    {
        long earthRadius = 6371; //radius in km
        double lat = toDegrees(asin(v.getZ() / earthRadius));
        double lon = toDegrees(atan2(v.getY(), v.getX()));

        return new double[] {lat, lon};
    }



}

