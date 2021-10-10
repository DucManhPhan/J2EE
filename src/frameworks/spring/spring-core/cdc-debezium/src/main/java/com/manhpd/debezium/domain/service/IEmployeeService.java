package com.manhpd.debezium.domain.service;

import com.manhpd.debezium.persistence.entity.EmployeeEntity;

import java.util.List;

public interface IEmployeeService {

    public List<EmployeeEntity> getAllEmployees();

    public EmployeeEntity saveEmployee(EmployeeEntity emp);

}
