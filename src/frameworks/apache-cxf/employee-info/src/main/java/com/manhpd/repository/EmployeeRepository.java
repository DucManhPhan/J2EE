package com.manhpd.repository;

import com.manhpd.dto.EmployeeDto;
import com.manhpd.dto.UpdateEmployeeData;

import java.util.List;

public interface EmployeeRepository {

    List<EmployeeDto> findAllEmployees();

    void updateEmployeeWithId(int id, UpdateEmployeeData data);

}
