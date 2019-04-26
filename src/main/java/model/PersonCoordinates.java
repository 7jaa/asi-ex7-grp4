package model;

public class PersonCoordinates {
    private Long id;
    private Double geoPosLat;
    private Double geoPosLon;
    private Boolean zone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGeoPosLat() {
        return geoPosLat;
    }

    public void setGeoPosLat(Double geoPosLat) {
        this.geoPosLat = geoPosLat;
    }

    public Double getGeoPosLon() {
        return geoPosLon;
    }

    public void setGeoPosLon(Double geoPosLon) {
        this.geoPosLon = geoPosLon;
    }

    public Boolean getZone() {
        return zone;
    }

    public void setZone(Boolean zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("id       : " + this.id);
        sb.append("\n");
        sb.append("latitude : " + this.geoPosLat);
        sb.append("\n");
        sb.append("longitude: " + this.geoPosLon);
        sb.append("\n");
        sb.append("zone     : " + this.zone);
        sb.append("\n---");

        return sb.toString();
    }
}
