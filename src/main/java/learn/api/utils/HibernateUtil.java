package learn.api.utils;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    @Getter
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration().configure();
            sessionFactory = config.buildSessionFactory();
        } catch (RuntimeException e) {
            new RuntimeException(e);
            e.printStackTrace();
        }
    }

    public static Session getSession() {
       if (sessionFactory != null) {
           return sessionFactory.openSession();
       }
       return null;
    }
}
