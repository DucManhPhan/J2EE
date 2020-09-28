package com.manhpd.distlockingredis.ws;

import com.manhpd.distlockingredis.domain.service.IEmployeeService;
import com.manhpd.distlockingredis.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @GetMapping(value = "/employees")
    public List<EmployeeDto> findByAll() {
        return this.employeeService.findByAll();
    }

    @GetMapping(value = "/start-job")
    public void startScanJob() {

    }

}
