package com.manhpd.shared.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static final Logger logger = LogManager.getLogger(HibernateUtils.class);

    private static final SessionFactory sessionFactory = buildSessionFactory();

    @SuppressWarnings("deprecation")
    private static SessionFactory buildSessionFactory() {
        logger.info("Start to build session factory for Hibernate");
        try {
            logger.info("End with building session factory of Hibernate");
            return new Configuration().configure(
                                            HibernateUtils.class.getResource("/hibernate.cfg__.xml")
//                                            HibernateUtils.class.getResource("/hibernate.properties")
                                        ).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
