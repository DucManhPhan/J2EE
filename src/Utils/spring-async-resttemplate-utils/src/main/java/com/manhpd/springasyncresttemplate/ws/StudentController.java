package com.manhpd.springasyncresttemplate.ws;


import com.manhpd.springasyncresttemplate.domain.domain_object.Student;
import com.manhpd.springasyncresttemplate.domain.service.AsyncHttpClientService;
import com.manhpd.springasyncresttemplate.domain.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Value("${timeout}")
    private long timeout;

    @Autowired
    private StudentService studentService;

    @Autowired
    private AsyncHttpClientService asyncHttpClientService;

    @GetMapping("/students")
    public String getAllStudents() throws InterruptedException {
        Thread.sleep(this.timeout);
        return this.studentService.getAllStudents();
    }

    @PostMapping("/students/{name}")
    public String getStudentByName(@PathVariable String name) throws InterruptedException {
        Thread.sleep(this.timeout);
        String tmp = this.studentService.findStudentByName(name);

        return tmp;
    }

    @GetMapping("/async/students")
    public String getAsyncStudents() {
        return this.asyncHttpClientService.getAsyncStudentsRequest();
    }

    @PostMapping("/async/students/{name}")
    public String postNameStudent(@PathVariable String name) {
        return this.asyncHttpClientService.postAsyncStudentRequest(name);
    }

}
