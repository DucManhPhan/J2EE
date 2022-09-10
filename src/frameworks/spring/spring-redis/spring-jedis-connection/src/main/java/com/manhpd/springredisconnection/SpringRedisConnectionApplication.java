package com.manhpd.springredisconnection;

import com.manhpd.springredisconnection.redis.entity.Student;
import com.manhpd.springredisconnection.redis.repository.StudentCacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class SpringRedisConnectionApplication implements CommandLineRunner {

    @Autowired
    private StudentCacheRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringRedisConnectionApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Student student = new Student("Eng2015001", "John Doe", Student.Gender.MALE, 1);
        this.studentRepository.save(student);

        Student retrievedStudent = studentRepository.findById("Eng2015001").get();

        log.info("Inserted student: " + student.toString());
        log.info("Retrieved student: " + retrievedStudent.toString());
    }
}
