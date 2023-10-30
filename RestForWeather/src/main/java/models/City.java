package models;
import jakarta.persistence.*;
@Entity
@Table(name = "City")
public class City {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(name = "name")
    public String name;
    @Column(name = "latitude")
    public String latitude;
    @Column(name = "longitude")
    public String longitude;
    @OneToOne(mappedBy = "city")
    public Weather weather;

    public City(String name, String latitude, String longitude, Weather weather) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.weather = weather;
    }

    public City() {}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
