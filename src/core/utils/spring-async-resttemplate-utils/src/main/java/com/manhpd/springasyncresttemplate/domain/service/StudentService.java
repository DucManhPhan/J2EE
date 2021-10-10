package com.manhpd.springasyncresttemplate.domain.service;


import com.manhpd.springasyncresttemplate.domain.domain_object.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private static final List<Student> students = new ArrayList<Student>() {{
        add(new Student("Obama", 62, "NewYork"));
        add(new Student("Bill Clinton", 62, "NewYork"));
        add(new Student("Bill Gate", 62, "Ohama"));
        add(new Student("Google", 62, "NewYork"));
    }};

    public String getAllStudents() {
        return students.toString();
    }

    public String findStudentByName(String name) {
        Optional<Student> optStudent = students.stream()
                .filter(student -> student.getName().equals(name))
                .findFirst();
        return optStudent.isPresent() ? optStudent.get().toString() : "Do not exist student with " + name;
    }

}
