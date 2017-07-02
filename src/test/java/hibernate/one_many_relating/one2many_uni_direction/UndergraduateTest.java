package hibernate.one_many_relating.one2many_uni_direction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2017/3/23.
 */
public class UndergraduateTest {
    private Configuration cfg = new Configuration();
    private SessionFactory sFactory = cfg.configure().buildSessionFactory();

    @Test
    public void saveTest() {
        Session session = sFactory.getCurrentSession();
        session.beginTransaction();

        Undergraduate undergraduate = new Undergraduate();
        undergraduate.setName("Silver");

        Dream dream1 = new Dream();
        Dream dream2 = new Dream();
        dream1.setDescription("earn an enormous amount of money");
        dream2.setDescription("eat a lot delicious food");
        undergraduate.setDreams(new HashSet<>(Arrays.asList(dream1, dream2)));

        //session.save(dream1);
        //session.save(dream2);
        session.save(undergraduate);
        session.getTransaction().commit();

    }

}