package com.manhpd.debezium.domain.service.impl;

import com.manhpd.debezium.persistence.repository.EmployeeRedisRepository;
import com.manhpd.debezium.utils.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmployeeRedisService {

    @Autowired
    private EmployeeRedisRepository redisRepository;

    public void interactWithRedisBasedOn(Map<String, Object> employeeData, Operation operation) {
        System.out.println("Employee Redis Service is called with: " + employeeData.toString());
    }

}
