package application.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DAO {
    private static final ThreadLocal<Session> session = new ThreadLocal<Session>();
    private static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public static Session getSession() {
        Session currentSession = session.get();
        if (currentSession == null || !currentSession.isOpen()) {
            currentSession = sessionFactory.openSession();
            session.set(currentSession);
        }
        return currentSession;
    }

    public static void begin() {
        Session currentSession = getSession();
        if (currentSession != null) {
            currentSession.beginTransaction();
        }
    }

    public static void commit() {
        Session currentSession = getSession();
        if (currentSession != null) {
            Transaction transaction = currentSession.getTransaction();
            if (transaction != null && transaction.isActive()) {
                transaction.commit();
            }
        }
    }

    public static void rollback() {
        Session currentSession = getSession();
        if (currentSession != null) {
            Transaction transaction = currentSession.getTransaction();
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
