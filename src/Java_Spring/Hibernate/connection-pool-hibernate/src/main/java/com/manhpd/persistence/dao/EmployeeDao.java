package com.manhpd.persistence.dao;

import com.manhpd.persistence.entity.Employee;
import com.manhpd.shared.utils.HibernateUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EmployeeDao implements IEmployeeDao {

    private final static Logger logger = LogManager.getLogger(EmployeeDao.class);

    private static SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public List<Employee> findAll() {
        Session session = sessionFactory.openSession();
        List<Employee> emps = Collections.emptyList();
        Transaction tx = null;

        try {
            tx = session.getTransaction();
            emps = session.createQuery("SELECT e FROM Employee e").list();
            tx.commit();
        } catch (HibernateException ex) {
            logger.error("findAll() method of EmployeeDao class: ", ex);
            if (Objects.nonNull(tx)) {
                tx.rollback();
            }
        }

        return emps;
    }

    @Override
    public Employee findById(int id) {
        return null;
    }

    @Override
    public void update(Employee emp) {

    }
}
