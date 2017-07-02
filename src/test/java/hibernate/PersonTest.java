package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2017/3/16.
 */
public class PersonTest {
    private Configuration cfg = new Configuration();
    private SessionFactory sFactory = cfg.configure().buildSessionFactory();

    @Test
    public void testSave() {
        Session session = sFactory.getCurrentSession();
        session.beginTransaction();
        session.save(new Person(1,"Silver","worker"));
        session.save(new Person(2,"Narcissus","student"));
        session.save(new Person(3,"good!","student"));
        session.getTransaction().commit();


    }

}