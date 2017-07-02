package hibernate;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaExportTask;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2017/3/14.
 */
public class StudentTest {
    private Configuration cfg = new Configuration();
    private SessionFactory sFactory = cfg.configure().buildSessionFactory();

    @Test
    public void saveTest() {
        Session session = sFactory.openSession();
        session.beginTransaction();
        session.save(new Student("Silver", 20, LocalDate.parse("1997-01-04"), Grade.UNDERGRADUATE));
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void updateTest() {
        Session session = sFactory.openSession();
        session.beginTransaction();
        session.save(new Student("Silver", 20, LocalDate.parse("1997-01-04"), Grade.UNDERGRADUATE));
        session.getTransaction().commit();
        session.close();

        Session session2 = sFactory.openSession();
        session2.beginTransaction();
        Student student = session2.load(Student.class, 1);
        student.setAge(21);
        session2.getTransaction().commit();
        session2.close();
    }

    @Test
    public void testSchemaExport() {
        SchemaExport schemaExport = new SchemaExport();
    }

    @Test
    public void saveAndDeleteTest() {
        Session session = sFactory.getCurrentSession();
        session.beginTransaction();
        session.save(new Student("Silver", 20, LocalDate.parse("1997-01-04"), Grade.UNDERGRADUATE));
        session.save(new Student("Narcissus", 20, LocalDate.parse("1997-01-04"), Grade.UNDERGRADUATE));
        session.getTransaction().commit();

//        Session session2 = sFactory.getCurrentSession();
//        session2.beginTransaction();
//        Student student = new Student();
//        student.setId(1);
//        session2.delete(student);
//        session2.save(new Student("new Narcissus", 20, LocalDate.parse("1997-01-04"), Grade.UNDERGRADUATE));
//
//        session2.getTransaction().commit();

        Session session3 = sFactory.getCurrentSession();
        session3.beginTransaction();
        Student student3 = new Student();
        student3 = session3.get(Student.class, 1);
        //session3.save(new Student("new Narcissus", 20, LocalDate.parse("1997-01-04"), Grade.UNDERGRADUATE));
        System.out.println(student3.getGrade());
        session3.getTransaction().commit();
    }
}