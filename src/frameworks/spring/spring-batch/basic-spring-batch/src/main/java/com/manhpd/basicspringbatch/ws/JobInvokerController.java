package com.manhpd.basicspringbatch.ws;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInvokerController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job processJob;

    @RequestMapping("/invokeJob")
    public String handle() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                                                                .toJobParameters();
        this.jobLauncher.run(this.processJob, jobParameters);
        return "Batch job has been invoked!";
    }

}
