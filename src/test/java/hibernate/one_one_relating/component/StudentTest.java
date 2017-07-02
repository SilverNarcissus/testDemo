package hibernate.one_one_relating.component;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2017/3/23.
 */
public class StudentTest {
    private Configuration cfg = new Configuration();
    private SessionFactory sFactory = cfg.configure().buildSessionFactory();

    @Test
    public void saveTest(){
        Session session = sFactory.getCurrentSession();
        session.beginTransaction();

        Student student=new Student();
        student.setName("Silver");
        student.setBirthday(LocalDate.parse("1997-01-04"));
        student.setCard(new StudentCard(LocalDate.parse("2015-09-01")));

        session.save(student);
        session.getTransaction().commit();

    }

}