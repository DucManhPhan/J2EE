package com.manhpd.springasyncresttemplate.domain.service;


import com.manhpd.springasyncresttemplate.domain.domain_object.RequestContent;
import com.manhpd.springasyncresttemplate.shared.AsyncRestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class AsyncHttpClientService {

    @Value("${timeout}")
    private long timeout;

    @Value("${async.test.students.url}")
    private String wsStudents;

    @Autowired
    private AsyncRestTemplateUtils asyncRestTemplateUtils;

    public String getAsyncStudentsRequest() {
        String response = "Internal Error System";
        RequestContent requestContent = RequestContent.builder()
                .url(this.wsStudents)
                .build();
        Future<String> future = this.asyncRestTemplateUtils.getRequest(requestContent);

        try {
            response = future.get(this.timeout, TimeUnit.MILLISECONDS).toString();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Exception: " + e);
        } catch (TimeoutException e) {
            response = "It happens timeout";
        }

        return response;
    }

    public String postAsyncStudentRequest(String name) {
        String response = "Internal Error System";
        RequestContent requestContent = RequestContent.builder()
                .url(this.wsStudents + "/" + name)
                .build();
        Future<String> future = this.asyncRestTemplateUtils.postRequest(requestContent);

        try {
            boolean isDone = false;
            while (!(isDone = future.isDone())) {
                response = future.get(this.timeout, TimeUnit.MILLISECONDS);
                System.out.println("Calculating ...");
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Exception: " + e);
        } catch (TimeoutException e) {
            response = "It happens timeout";
        }

        return response;
    }

}
