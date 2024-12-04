package API.database.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Configure Hibernate and build the SessionFactory
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception using a logging framework (optional)
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session openSession() {
        return getSessionFactory().openSession();
    }

    public static void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    // Optional: Method to close SessionFactory
    public static void shutdown() {
        // Close caches and connection pools
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}