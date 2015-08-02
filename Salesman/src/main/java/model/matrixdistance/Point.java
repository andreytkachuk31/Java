package model.matrixdistance;

/**
 * Date: 11.05.13
 *
 * @author andrey.tkachuk
 */
public class Point {

    private double lat;
    private double lng;

    public Point(final double lat, final double lng) {
        this.lng = lng;
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return lat + "," + lng;
    }
}
