package com.manhpd;

import com.manhpd.persistence.entity.Employee;
import com.manhpd.shared.utils.HibernateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.io.IOException;

public class App {

    private final static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) throws IOException {
        logger.info("Start to go with Hibernate");
        Session session = HibernateUtils.getSessionFactory().openSession();
//        Properties prop = DatabaseConfig.INSTANCE.loadDatabaseConfig();
//        Session session = HibernateUtils.getSessionFactory(prop).getCurrentSession();
        session.beginTransaction();

        Employee employee = new Employee("Obama", 53);
        session.save(employee);

        session.getTransaction().commit();
        HibernateUtils.shutdown();

        logger.info("End");
    }
}
