package dao;
import models.Weather;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.sql.*;
import java.util.List;
public class WeatherDAO {
    private static Transaction transaction = null;
    private static final Session session = HibernateUtil.getSessionFactory().openSession();
    public static void save(Weather weather) throws SQLException {
        boolean o = getWeatherByID(weather);
        transaction = session.beginTransaction();
        if(o)
            session.createQuery("UPDATE Weather w set temperature =" + weather.getTemperature() + ", " +
                    "feels_like =" + weather.getFeels_like() + ", main ='" + weather.getMain() + "', description ='" + weather.getDescription() + "' where w.city.id =" + weather.getCity().getId()).executeUpdate();
        else
            session.persist(weather);
        transaction.commit();
    }
    public static Boolean getWeatherByID(Weather weather) throws SQLException {
        transaction = session.beginTransaction();
        List<Weather> weatherList = session.createQuery("SELECT w from Weather w where w.city.id =" + weather.getCity().id, Weather.class)
                .getResultList();
        transaction.commit();
        if (weatherList.size() == 0){
            return false;
        }
        return weatherList.get(0).getCity().getId() == weather.getCity().getId();
    }

    public static List<Weather> getWeather() throws SQLException {
        transaction = session.beginTransaction();
        List<Weather> weatherList = session.createQuery("from Weather", Weather.class)
                .getResultList();
        transaction.commit();
        return weatherList;
    }

    public static Weather getWeatherWithMaxTemperature() throws SQLException {
        transaction = session.beginTransaction();
        List<Weather> weatherList = session.createQuery("from Weather where temperature=(SELECT max(temperature) from Weather)", Weather.class)
                .getResultList();
        transaction.commit();
        return weatherList.get(0);
    }
    public static Weather getWeatherWithMinTemperature() throws SQLException {
        transaction = session.beginTransaction();
        List<Weather> weatherList = session.createQuery("from Weather where temperature=(SELECT min(temperature) from Weather)", Weather.class)
                .getResultList();
        transaction.commit();
        return weatherList.get(0);
    }
}
