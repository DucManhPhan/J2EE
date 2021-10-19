package com.manhpd.basicspringbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class BasicSpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicSpringBatchApplication.class, args);
    }

}
