package studyandroid.it.vietung.amthucbonphuong.maps;

/**
 * Created by VietUng on 22/01/2016.
 */
public class Places {
    private String name;

    public Places(String name, double lat, double lng, String vicinity) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.vicinity = vicinity;
    }

    public Places() {

    }

    public String getVicinity() {

        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    private String vicinity;

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private double lat;
    private double lng;

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
}
