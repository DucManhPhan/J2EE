package com.manhpd.service;

import com.manhpd.dto.EmployeeDto;
import com.manhpd.dto.UpdateEmployeeData;
import com.manhpd.dto.UpdateEmployeeStatus;
import com.manhpd.repository.EmployeeRepository;
import org.apache.camel.PropertyInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @PropertyInject("endpoint.external.main")
    private String externalEndpoint;

    @Autowired
    EmployeeRepository repository;

    public List<EmployeeDto> getEmployee() {
        return this.repository.findAllEmployees();
    }

    public UpdateEmployeeStatus updateEmployeeWithId(int id, UpdateEmployeeData data) {
        UpdateEmployeeStatus status = UpdateEmployeeStatus.builder()
                .errorCode("200")
                .message("Successfully")
                .build();

        this.repository.updateEmployeeWithId(id, data);
        return status;
    }

}
