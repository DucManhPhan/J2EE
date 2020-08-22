package com.manhpd.debezium.ws;

import com.manhpd.debezium.persistence.entity.EmployeeRedis;
import com.manhpd.debezium.persistence.repository.EmployeeRedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisInfoController {

    @Autowired
    private EmployeeRedisRepository redisRepository;

    @PostMapping("/publish")
    public void sendMessageRedis(@RequestParam("message") String message) {
        EmployeeRedis employeeRedis = new EmployeeRedis(Long.valueOf(123), "Google.com",
                                              "com", "gmail.com",
                                          "123344123123", "ABC");
        System.out.println("Save data to redis");
        this.redisRepository.save(employeeRedis);
    }

}
