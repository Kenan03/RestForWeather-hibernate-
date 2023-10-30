package dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainWeatherInfo {
    private Double temp;
    private Double feels_like;

    public MainWeatherInfo(Double temp, Double feels_like) {
        this.temp = temp;
        this.feels_like = feels_like;
    }
    public MainWeatherInfo() {
    }
    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }
    public Double getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(Double feels_like) {
        this.feels_like = feels_like;
    }
    @Override
    public String toString() {
        return "MainWeatherInfo{" +
                "temp=" + temp +
                ", feels_like=" + feels_like +
                '}';
    }
}
