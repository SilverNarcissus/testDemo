package hibernate;

import hibernate.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class StudentDriver {

    public static void main(String[] args) throws Exception {
        Student students = new Student(21,"ASD", 130);
        Configuration cfg = new Configuration();


        SessionFactory sFactory = cfg.configure().buildSessionFactory();
        Session session = sFactory.openSession();

        session.beginTransaction();
        try {
            session.update(students);
        }
        catch (Exception e){
            System.out.println(e.getClass().getName());
        }
//        Object o=students;
        session.getTransaction().commit();
session.close();
        System.out.println(session.contains(students));
        //查询
//        System.out.println(students.getClass().getName());
//       Criteria c = session.createCriteria(Class.forName("hibernate.Student"));
//        c.add(Restrictions.like("age", 1));
//        List<Student> l = c.list();
//        for(Student s:l) {
//            System.out.println(s.getName());
//        }
    }
}
