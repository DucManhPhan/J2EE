package com.manhpd.springlettuceconnection;

import com.manhpd.springlettuceconnection.service.RedisOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLettuceConnectionApplication implements CommandLineRunner {

    @Autowired
    private RedisOperationsService redisOperationsService;

    public static void main(String[] args) {
        SpringApplication.run(SpringLettuceConnectionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        this.redisOperationsService.insertNormalKeyValue();
//        this.redisOperationsService.insertHashValue();
        this.redisOperationsService.insertListValue();
    }

}
