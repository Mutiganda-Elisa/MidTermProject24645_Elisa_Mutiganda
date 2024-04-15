package dao;

import model.AcademicUnit;
import model.Course;
import model.Semester;
import model.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CourseDAO {

    private SessionFactory sessionFactory;

    public CourseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createCourse(Course course) {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
            session.close();
        }
    }

    public List<Course> getAllCourses() {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            Query<Course> query = session.createQuery("FROM Course", Course.class);
            List<Course> courses = query.getResultList();
            session.close();
            return courses;
        }
        return null;
    }

    public Course getCourseById(Long id) {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            Course course = session.get(Course.class, id);
            session.close();
            return course;
        }
        return null;
    }

    public void updateCourse(Course course) {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(course);
            transaction.commit();
            session.close();
        }
    }

    public void deleteCourse(Long id) {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Course course = session.get(Course.class, id);
            session.delete(course);
            transaction.commit();
            session.close();
        }
    }

    public List<Semester> getAllSemesters() {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            Query<Semester> query = session.createQuery("FROM Semester", Semester.class);
            List<Semester> semesters = query.getResultList();
            session.close();
            return semesters;
        }
        return null;
    }

    public Semester getSemesterById(Long id) {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            Semester semester = session.get(Semester.class, id);
            session.close();
            return semester;
        }
        return null;
    }

    public List<AcademicUnit> getAllAcademicUnits() {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            Query<AcademicUnit> query = session.createQuery("FROM AcademicUnit", AcademicUnit.class);
            List<AcademicUnit> academicUnits = query.getResultList();
            session.close();
            return academicUnits;
        }
        return null;
    }

    public AcademicUnit getAcademicUnitById(Long id) {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            AcademicUnit academicUnit = session.get(AcademicUnit.class, id);
            session.close();
            return academicUnit;
        }
        return null;
    }

    public List<Teacher> getAllTeachers() {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            Query<Teacher> query = session.createQuery("FROM Teacher", Teacher.class);
            List<Teacher> teachers = query.getResultList();
            session.close();
            return teachers;
        }
        return null;
    }

    public Teacher getTeacherById(Long id) {
        if (sessionFactory != null) {
            Session session = sessionFactory.openSession();
            Teacher teacher = session.get(Teacher.class, id);
            session.close();
            return teacher;
        }
        return null;
    }
}