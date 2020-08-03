package com.manhpd.debezium.persistence.entity;

import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Employee")
public class EmployeeRedis implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String desgination;

    public EmployeeRedis(Long id, String firstName,
                         String lastName, String email,
                         String phoneNumber, String desgination) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.desgination = desgination;
    }
}
