package com.manhpd.distlockingredis.domain.service;

import com.manhpd.distlockingredis.dto.EmployeeDto;

import java.util.List;

public interface IEmployeeService {

    List<EmployeeDto> findByAll();

}
