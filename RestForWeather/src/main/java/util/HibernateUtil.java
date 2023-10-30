package util;
import models.City;
import models.Person;
import models.Weather;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try{
                FileInputStream fis = new FileInputStream("D:\\Java Ultimate Projects\\RestForWeather\\src\\main\\resources\\database.properties");
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.load(fis);
                Class.forName(properties.getProperty("hibernate.driver_class"));
                properties.getProperty("hibernate.connection.url");
                properties.getProperty("hibernate.connection.username");
                properties.getProperty("hibernate.connection.password");
                properties.put("hibernate.dialect", properties.getProperty("hibernate.dialect"));
                properties.put("hibernate.show_sql", properties.getProperty("hibernate.show_sql"));
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(Person.class);
                configuration.addAnnotatedClass(City.class);
                configuration.addAnnotatedClass(Weather.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            }
            catch (IOException | ClassNotFoundException e) {
               throw new RuntimeException(e);
           }
        }
        return sessionFactory;
    }

}