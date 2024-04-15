package dao;

import model.Teacher;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Arrays;
import java.util.List;
import model.EQualification;
import model.HibernateTest;


public class TeacherDAO {

    public void create(Teacher teacher) {
        Session session = HibernateTest.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(teacher);
        transaction.commit();
        session.close();
    }

    public Teacher read(Long teacherId) {
        Session session = HibernateTest.sessionFactory.openSession();
        Teacher teacher = session.get(Teacher.class, teacherId);
        session.close();
        return teacher;
    }

    public void update(Teacher teacher) {
        Session session = HibernateTest.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(teacher);
        transaction.commit();
        session.close();
    }

    public void delete(Teacher teacher) {
        Session session = HibernateTest.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(teacher);
        transaction.commit();
        session.close();
    }

 
    
    public List<EQualification> getAllQualifications() {
        return Arrays.asList(EQualification.values());
    }
    
    
    
        public static List<Teacher> getAllTeachers() {
            Session session = HibernateTest.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            try {
                List<Teacher> teachers = session.createQuery("FROM Teacher", Teacher.class).getResultList();
                transaction.commit();
                return teachers;
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

        public static Teacher getTeacherById(Long teacherId) {
            Session session = HibernateTest.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            try {
                Teacher teacher = session.get(Teacher.class, teacherId);
                transaction.commit();
                return teacher;
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
    }

      

