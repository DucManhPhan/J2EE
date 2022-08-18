package com.manhpd.linkedinspringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableBatchProcessing
public class LinkedinSpringBatchApplication {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step packageItemStep() {
        return this.stepBuilderFactory.get("packageItemStep").tasklet((stepContribution, chunkContext) -> {
            System.out.println("The item has been packaged");
            return RepeatStatus.FINISHED;
        }).build();
    }

    @Bean
    public Job deliverPackageJob() {
        return this.jobBuilderFactory.get("deliverPackageJob")
                                     .start(packageItemStep())
                                     .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(LinkedinSpringBatchApplication.class, args);
    }

}
