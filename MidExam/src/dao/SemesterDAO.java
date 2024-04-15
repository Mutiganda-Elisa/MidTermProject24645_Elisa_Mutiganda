package dao;

import model.HibernateTest;

import model.Semester;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;





public class SemesterDAO {
    public static List<Semester> getAllSemesters() {
        Session session = HibernateTest.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            List<Semester> semesters = session.createQuery("FROM Semester", Semester.class).getResultList();
            transaction.commit();
            return semesters;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public static Semester getSemesterById(Long semesterId) {
        Session session = HibernateTest.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Semester semester = session.get(Semester.class, semesterId);
            transaction.commit();
            return semester;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public void create(Semester semester) {
        Session session = HibernateTest.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(semester);
        transaction.commit();
        session.close();
    }

    public Semester read(Long semesterId) {
        Session session = HibernateTest.sessionFactory.openSession();
        Semester semester = session.get(Semester.class, semesterId);
        session.close();
        return semester;
    }

    public void update(Semester semester) {
        Session session = HibernateTest.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(semester);
        transaction.commit();
        session.close();
    }

    public void delete(Semester semester) {
        Session session = HibernateTest.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(semester);
        transaction.commit();
        session.close();
    }

  
    
    
}