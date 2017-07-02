package hibernate.one_one_relating;

import hibernate.one_one_relating.FK_related.Husband;
import hibernate.one_one_relating.FK_related.Wife;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2017/3/22.
 */
public class One_One_Test {
    private Configuration cfg = new Configuration();
    private SessionFactory sFactory = cfg.configure().buildSessionFactory();
    @Test
    public void testCreate() {
        Session session = sFactory.getCurrentSession();
        session.beginTransaction();
        Husband husband=new Husband();
        husband.setName("Narcissus");
        Wife wife=new Wife();
        wife.setName("Silver");
        husband.setWife(wife);
        Wife wife1=new Wife();
        wife1.setName("Silver2");
        session.save(wife1);
        session.save(wife);
        session.save(husband);

        session.getTransaction().commit();
    }
}