package Servlet;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CityDAO;
import dao.PersonDAO;
import dao.WeatherDAO;
import dto.CurrentWeatherResponseDTO;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.City;
import models.Person;
import models.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class TestingServlet extends HttpServlet {
    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private CurrentWeatherResponseDTO currentWeatherResponseDTO;

    public void init(ServletConfig config){

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        String uri = request.getRequestURI();
        System.out.println();

        if(uri.equals("/weather")){
            HttpSession session = request.getSession();
            int id = (int) session.getAttribute("id");
            try {
                Person person = PersonDAO.getUserByID(id);
                out.print("<H4> Hello, " + person.getName() + " Welcome to Profile!</H4>");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            request.getRequestDispatcher("city.html").include(request, response);
        }

        if(uri.equals("/weather/analysis")){
            String url = "";
            try {
                List<String> list = CityDAO.listOfCity();
                for(int i = 0; i < list.size(); i++){
                    url = getUrl(list.get(i));
                    City city = null;
                    try {
                        city = CityDAO.city(list.get(i));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    // Создание GET-запроса
                    Request request1 = new Request.Builder()
                            .url(url)
                            .build();
                    // Выполнение запроса
                    Response response1 = client.newCall(request1).execute();

                    // Обработка ответа
                    if (response1.isSuccessful()) {
                        currentWeatherResponseDTO = getCurrentWeatherResponseDTO(response1);
                        Weather weather = new Weather((int) (currentWeatherResponseDTO.getMain().getTemp() - 273.15), (int)(currentWeatherResponseDTO.getMain().getFeels_like() - 273.15), currentWeatherResponseDTO.getWeather().get(0).getMain(), currentWeatherResponseDTO.getWeather().get(0).getDescription(), city);
                        WeatherDAO.save(weather);
                    } else {
                        System.out.println("Request failed: " + response1.code());
                    }
                }
                List<Weather> weathers = WeatherDAO.getWeather();
                Weather weather = WeatherDAO.getWeatherWithMaxTemperature();
                out.println("<html>\n<body>");
                out.println("<b> The warmest city is " + CityDAO.cityByID(weather.getCity().id).getName() + ": </b> " +
                        weather.getMain() + ", " + weather.getDescription() + ". ");
                out.println(" Temperature: " + weather.getTemperature() + "', feels like: " +  weather.getFeels_like() + "' ");
                weathers.remove(weather);
                Weather weather1 = WeatherDAO.getWeatherWithMinTemperature();
                out.println("<p><b> The coldest city is " + CityDAO.cityByID(weather1.getCity().id).getName() + ": </b> " +
                        weather1.getMain() + ", " + weather1.getDescription() + ". ");
                out.println(" Temperature: " + weather1.getTemperature() + "', feels like: " +  weather1.getFeels_like() + "'</p> ");
                weathers.remove(weather);
                out.println("<p>Weather in other cities: </p>");
                for(int i = 0; i < weathers.size(); i++) {
                    if(weathers.get(i).getCity().id != weather.getCity().id && weathers.get(i).getCity().id != weather1.getCity().id) {
                        out.println("<p><b>" + CityDAO.cityByID(weathers.get(i).getCity().id).getName() + ": </b> " +
                                weathers.get(i).getMain() + ", " + weathers.get(i).getDescription() + ". ");
                        out.println(" Temperature: " + weathers.get(i).getTemperature() + "', feels like: " + weathers.get(i).getFeels_like() + "'</p> ");
                    }
                }
                out.println("<a href=" + "/weather" + ">Home page!</a>" + " " + "</body>\n</html>");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String cityFromRequest = (request.getParameter("city"));
        String uri = request.getRequestURI();
        System.out.println();
        PrintWriter out = response.getWriter();

        if(uri.equals("/weather/current")){
            String url = getUrl(cityFromRequest);
            City city = null;
            try {
                city = CityDAO.city(cityFromRequest);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            // Создание GET-запроса
            Request request1 = new Request.Builder()
                    .url(url)
                    .build();

            // Выполнение запроса
            Response response1 = client.newCall(request1).execute();

            // Обработка ответа
            if (response1.isSuccessful()) {
                currentWeatherResponseDTO = getCurrentWeatherResponseDTO(response1);
                Weather weather = new Weather((int) (currentWeatherResponseDTO.getMain().getTemp() - 273.15), (int)(currentWeatherResponseDTO.getMain().getFeels_like() - 273.15), currentWeatherResponseDTO.getWeather().get(0).getMain(), currentWeatherResponseDTO.getWeather().get(0).getDescription(), city);
                try {
                    WeatherDAO.save(weather);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                out.println("<html>\n<body>");
                out.println("<h4> Current weather in " + city.getName() + ": </h4> <p>" + currentWeatherResponseDTO.getWeather().get(0).getMain() +
                        ", " + currentWeatherResponseDTO.getWeather().get(0).getDescription() + ". ");
                out.println(" Temperature: " + weather.getTemperature() +
                "', feels like: " +  weather.getFeels_like() + "'</p> ");
                out.println("<a href=" + "/weather" + ">Home page!</a>" + " " + "</body>\n</html>");
            } else {
                System.out.println("Request failed: " + response1.code());
            }
        }
    }
    private String getUrl(String cityFromRequest) {
        // URL с параметрами
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.openweathermap.org/data/2.5/weather").newBuilder();

        City city = null;
        try {
            city = CityDAO.city(cityFromRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        urlBuilder.addQueryParameter("lat", city.getLatitude());
        urlBuilder.addQueryParameter("lon", city.getLongitude());

        urlBuilder.addQueryParameter("appid", "57734a93a26acbda889aa98c31d56213");
        return urlBuilder.build().toString();
    }
    public OkHttpClient getClient() {
        return client;
    }
    public CurrentWeatherResponseDTO getCurrentWeatherResponseDTO(Response response1) throws IOException {
        return currentWeatherResponseDTO = objectMapper.readValue(response1.body().string(), CurrentWeatherResponseDTO.class);
    }
}

