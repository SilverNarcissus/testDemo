import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;


public class TeacherDirver {
	public static void main(String[] args) {

		Teacher teacher = new Teacher(1, "KOK", 40);
		Configuration cfg = new AnnotationConfiguration();

		SessionFactory sFactory = cfg.configure().buildSessionFactory();
		Session session = sFactory.openSession();
		session.beginTransaction();
		session.save(teacher);
		session.getTransaction().commit();
	}
}
