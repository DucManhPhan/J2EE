package com.manhpd.shared.utils;

import com.manhpd.persistence.entity.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtils {

    private static final Logger logger = LogManager.getLogger(HibernateUtils.class);

    private static final SessionFactory sessionFactory = buildSessionFactory();

    @SuppressWarnings("deprecation")
    private static SessionFactory buildSessionFactory() {
        logger.info("Start to build session factory for Hibernate");
        try {
            logger.info("End with building session factory of Hibernate");
            return new Configuration().configure(
                                            HibernateUtils.class.getResource("/hibernate.cfg.xml")
//                                            HibernateUtils.class.getResource("/hibernate.properties")
                                        ).buildSessionFactory();
        } catch (Exception ex) {
            logger.error("SessionFactory creation failed.", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static SessionFactory buildSessionFactory(Properties properties) {
        try {
            Configuration cfg = new Configuration();
//            cfg.configure(HibernateUtils.class.getResource("/hibernate.properties"));
            cfg.addAnnotatedClass(Employee.class);
            cfg.setProperties(properties);
            return cfg.buildSessionFactory();
        } catch (Exception ex) {
            logger.error("buildSessonFactory() method: ", ex);
        }

        return null;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory(Properties prop) {
        return buildSessionFactory(prop);
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
