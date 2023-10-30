package dao;
import models.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.sql.*;
import java.util.List;
public class PersonDAO {
    private static Transaction transaction = null;
    private static final Session session = HibernateUtil.getSessionFactory().openSession();
    public static Boolean findByName(String name) throws SQLException {
        transaction = session.beginTransaction();
        List<Person> personList = session.createQuery("from Person where name = '" + name + "'", Person.class)
                .getResultList();
        transaction.commit();
        return personList.size() != 0;
    }
    public static Boolean findByNameAndPassword(String name, String password) throws SQLException {
        transaction = session.beginTransaction();
        List<Person> personList = session.createQuery("from Person where name = '" + name + "' and password = '" + password + "'", Person.class)
                .getResultList();
        transaction.commit();
        return personList.size() != 0;
    }
    public static void save(Person person) throws SQLException {
        transaction = session.beginTransaction();
        session.persist(person);
        transaction.commit();
    }
    public static int getUserId(Person person) throws SQLException {
        transaction = session.beginTransaction();
        List<Person> personList = session.createQuery("from Person where name = '" + person.name + "'", Person.class)
                .getResultList();
        transaction.commit();
        return personList.get(0).getId();
    }
    public static Person getUserByID(int ID) throws SQLException {
        transaction = session.beginTransaction();
        List<Person> personList = session.createQuery("from Person where id = " + ID, Person.class)
                .getResultList();
        transaction.commit();
        return personList.get(0);
    }
    public static int getUserByName(String NAME) throws SQLException {
        transaction = session.beginTransaction();
        List<Person> personList = session.createQuery("from Person where name = '" + NAME + "'", Person.class)
                .getResultList();
        transaction.commit();
        return personList.get(0).getId();
    }
}
