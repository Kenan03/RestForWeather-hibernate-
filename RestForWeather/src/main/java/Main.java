import com.fasterxml.jackson.core.JsonProcessingException;

import dao.CityDAO;

import dao.WeatherDAO;
import models.Person;
import models.Weather;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException, SQLException {

//        Person person = new Person("dddd", "dddgbfgbsgd", "ffff");
//        PersonDAO.getUserByID(1);
//        CityDAO.city("Moscow");
        List<Weather> weathers = WeatherDAO.getWeather();
        weathers.get(0).setFeels_like(0);
        WeatherDAO.save(weathers.get(0));

//        Weather weather = WeatherDAO.getWeatherWithMinTemperature();
//        System.out.println(weather);
//        CityDAO cityDAO = new CityDAO();
//        System.out.println(CityDAO.city("Dubai").latitude);
//        System.out.println(CityDAO.city("Saint-Petersburg").longitude);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        String json = "{\"coord\":{\"lon\":30.19,\"lat\":59.57},\"weather\":[{\"id\":804,\"main\":\"Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":283.29,\"feels_like\":281.87,\"temp_min\":282.95,\"temp_max\":283.83,\"pressure\":1007,\"humidity\":58},\"visibility\":10000,\"wind\":{\"speed\":4.02,\"deg\":323,\"gust\":7.6},\"clouds\":{\"all\":93},\"dt\":1697106917,\"sys\":{\"type\":2,\"id\":2088492,\"country\":\"RU\",\"sunrise\":1697084937,\"sunset\":1697122946},\"timezone\":10800,\"id\":512047,\"name\":\"Pavlovskaya\",\"cod\":200}\n";
//
//
//        System.out.println(json);
////        JsonNode obj = objectMapper.readTree(json);
//        CurrentWeatherResponseDTO currentWeatherResponseDTO = objectMapper.readValue(json, CurrentWeatherResponseDTO.class);
//        System.out.println(currentWeatherResponseDTO);
//
//        System.out.println((float) 5/9 * (281.87 - 32));
//
        //        System.out.println(currentWeatherResponseDTO.getMainWeatherInfo());
//        System.out.println(obj.get("weather").get(0).get("id"));
//        System.out.println(obj.get("weather").get(0).get("main"));
//        System.out.println(obj.get("weather").get(0).get("description"));
//        System.out.println(obj.get("weather").get(0).get("icon"));

    }
}
