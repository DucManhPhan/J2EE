package com.manhpd.debezium.domain.service.impl;

import com.manhpd.debezium.domain.service.IEmployeeService;
import com.manhpd.debezium.persistence.entity.EmployeeEntity;
import com.manhpd.debezium.persistence.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository empRepository;

    @Override
    public List<EmployeeEntity> getAllEmployees() {
        return empRepository.findAll();
    }

    @Override
    public EmployeeEntity saveEmployee(EmployeeEntity emp) {
        return empRepository.save(emp);
    }

}
