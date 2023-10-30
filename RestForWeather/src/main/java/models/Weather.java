package models;
import jakarta.persistence.*;
@Entity
@Table(name = "weather")
public class Weather {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @OneToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    public City city;
    @Column(name = "temperature")
    public int temperature;
    @Column(name = "feels_like")
    public int feels_like;
    @Column(name = "main")
    public String main;
    @Column(name = "description")
    public String description;

    public Weather(int temperature, int feels_like, String main, String description, City city) {
        this.temperature = temperature;
        this.feels_like = feels_like;
        this.main = main;
        this.description = description;
        this.city = city;
    }
    public Weather() {

    }
    public int getTemperature() {
        return temperature;
    }
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
    public int getFeels_like() {
        return feels_like;
    }
    public void setFeels_like(int feels_like) {
        this.feels_like = feels_like;
    }
    public String getMain() {
        return main;
    }
    public void setMain(String main) {
        this.main = main;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    @Override
    public String toString() {
        return "Weather{" +
                ", temperature=" + temperature +
                ", feels_like=" + feels_like +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
