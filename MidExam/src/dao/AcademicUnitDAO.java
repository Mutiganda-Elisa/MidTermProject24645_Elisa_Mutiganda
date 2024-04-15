package dao;

import java.util.List;
import model.AcademicUnit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AcademicUnitDAO {
    private static SessionFactory sessionFactory;

    public AcademicUnitDAO(SessionFactory sessionFactory) {
        AcademicUnitDAO.sessionFactory = sessionFactory;
    }

    public void createAcademicUnit(AcademicUnit academicUnit) {
        try (Session session = AcademicUnitDAO.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(academicUnit);
            transaction.commit();
        }
    }

    public AcademicUnit getAcademicUnitById(Long academicId) {
        try (Session session = AcademicUnitDAO.sessionFactory.openSession()) {
            return session.get(AcademicUnit.class, academicId);
        }
    }

    public List<AcademicUnit> getAllAcademicUnits() {
        try (Session session = AcademicUnitDAO.sessionFactory.openSession()) {
            return session.createQuery("FROM AcademicUnit", AcademicUnit.class).getResultList();
        }
    }

    public void updateAcademicUnit(AcademicUnit academicUnit) {
        try (Session session = AcademicUnitDAO.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(academicUnit);
            transaction.commit();
        }
    }

    public void deleteAcademicUnit(Long academicId) {
        try (Session session = AcademicUnitDAO.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            AcademicUnit academicUnit = session.get(AcademicUnit.class, academicId);
            session.delete(academicUnit);
            transaction.commit();
        }
    }
}
