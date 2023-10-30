package dao;
import models.City;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.sql.*;
import java.util.List;
public class CityDAO {
    private static Transaction transaction = null;
    private static final Session session = HibernateUtil.getSessionFactory().openSession();

    public static City city(String cityName) throws SQLException {
        transaction = session.beginTransaction();
        List<City> weatherList = session.createQuery("from City where name = '" + cityName+ "'", City.class)
                .getResultList();
        transaction.commit();
        return weatherList.get(0);
    }
    public static City cityByID(int city_id) throws SQLException {
        transaction = session.beginTransaction();
        List<City> weatherList = session.createQuery("from City where id=" + city_id, City.class)
                .getResultList();
        transaction.commit();
        return weatherList.get(0);
    }
    public static List<String> listOfCity() throws SQLException {
        transaction = session.beginTransaction();
        List<String> list = session.createQuery("SELECT name from City", String.class)
                .getResultList();
        transaction.commit();
        return list;
    }
}
