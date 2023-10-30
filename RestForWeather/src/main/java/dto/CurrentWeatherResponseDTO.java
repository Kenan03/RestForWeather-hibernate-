package dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherResponseDTO {
    // для парсинга ответов этот класс нужен
    private List<Weather> weather;
    private MainWeatherInfo main;
    public CurrentWeatherResponseDTO(List<Weather> weather, MainWeatherInfo main) {
        this.weather = weather;
        this.main = main;
    }
    public CurrentWeatherResponseDTO() {
    }
    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
    public MainWeatherInfo getMain() {
        return main;
    }
    public void setMain(MainWeatherInfo main) {
        this.main = main;
    }
    @Override
    public String toString() {
        return "CurrentWeatherResponseDTO{" +
                "weather=" + weather +
                ", main=" + main +
                '}';
    }
}
