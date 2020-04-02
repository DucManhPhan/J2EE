package com.manhpd;

import com.manhpd.persistence.entity.Employee;
import com.manhpd.shared.utils.HibernateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

public class App {

    private final static Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        logger.info("Start to go with Hibernate");
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        Employee employee = new Employee("Obama", 53);
        session.save(employee);

        session.getTransaction().commit();
        HibernateUtils.shutdown();

        logger.info("End");
    }
}
