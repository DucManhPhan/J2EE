package com.manhpd.distlockingredis.domain.service;

import com.manhpd.distlockingredis.dto.EmployeeDto;
import com.manhpd.distlockingredis.persistence.entity.MpEmployee;
import com.manhpd.distlockingredis.persistence.repository.EmployeeRepository;
import com.manhpd.distlockingredis.utis.Constants;
import com.manhpd.distlockingredis.utis.ConverterUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private Jedis jedis;

    @Override
    public List<EmployeeDto> findByAll() {
        List<MpEmployee> mpEmployees = (List<MpEmployee>) this.employeeRepository.findAll();
        String isOk = this.jedis.set(Constants.EMPLOYEES_KEY, ConverterUtils.toString(mpEmployees));
        System.out.println(isOk);

        return this.toEmployeeDtos(mpEmployees);
    }

    private EmployeeDto toEmployeeDto(MpEmployee mpEmployee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(mpEmployee.getId());
        dto.setName(mpEmployee.getName());
        dto.setLastName(mpEmployee.getLastName());
        dto.setAge(mpEmployee.getAge());

        return dto;
    }

    private List<EmployeeDto> toEmployeeDtos(List<MpEmployee> employees) {
        return employees.stream().map(employee -> this.toEmployeeDto(employee))
                                 .collect(Collectors.toList());
    }

}
