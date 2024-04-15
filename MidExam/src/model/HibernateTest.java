package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import java.util.HashMap;
import java.util.Map;

public class HibernateTest {
	 public static SessionFactory sessionFactory;
  

	   static {
	        setup();
	    }
	 
	   public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	   
    public static void main(String[] args) {
        setup();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
           
            transaction.commit();
            System.out.println("Tables created successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    private static void setup() {
        Map<String, String> settings = new HashMap<>();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/auca_management_system?useSSL=false&serverTimezone=UTC");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "");
        settings.put(Environment.DEFAULT_CATALOG, "auca_management_system");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.HBM2DDL_AUTO, "update");

        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings)
                .build();

        MetadataSources metadataSources = new MetadataSources(standardRegistry);
        metadataSources.addAnnotatedClass(Student.class);
        metadataSources.addAnnotatedClass(Semester.class);
        metadataSources.addAnnotatedClass(Teacher.class);
        metadataSources.addAnnotatedClass(StudentRegistration.class);
        metadataSources.addAnnotatedClass(AcademicUnit.class);
        metadataSources.addAnnotatedClass(Course.class);
        metadataSources.addAnnotatedClass(CourseDefinition.class);
        metadataSources.addAnnotatedClass(User.class);

        Metadata metadata = metadataSources.buildMetadata();
        sessionFactory = metadata.buildSessionFactory();
    }
}