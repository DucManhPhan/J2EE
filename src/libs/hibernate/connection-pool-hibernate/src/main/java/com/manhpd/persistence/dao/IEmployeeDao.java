package com.manhpd.persistence.dao;

import com.manhpd.persistence.entity.Employee;

import java.util.List;

public interface IEmployeeDao {

    List<Employee> findAll();

    Employee findById(int id);

    void update(Employee emp);

}
