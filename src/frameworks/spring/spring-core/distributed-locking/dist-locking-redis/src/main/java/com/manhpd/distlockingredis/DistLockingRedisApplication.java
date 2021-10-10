package com.manhpd.distlockingredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistLockingRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistLockingRedisApplication.class, args);
    }

}
