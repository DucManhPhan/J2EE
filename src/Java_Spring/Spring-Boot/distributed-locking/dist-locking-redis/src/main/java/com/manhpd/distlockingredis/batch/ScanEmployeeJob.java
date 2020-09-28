package com.manhpd.distlockingredis.batch;

import com.manhpd.distlockingredis.domain.service.IEmployeeService;
import com.manhpd.distlockingredis.dto.EmployeeDto;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.List;

public class ScanEmployeeJob implements Runnable {

    private String nameJob;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private Jedis jedis;

    public ScanEmployeeJob(String nameJob) {
        this.nameJob = nameJob;
    }

    @Override
    public void run() {

        List<EmployeeDto> employeeDtos = this.employeeService.findByAll();
        if (CollectionUtils.isEmpty(employeeDtos)) {
            // release lock
        }


    }
}
