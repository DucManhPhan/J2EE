package com.manhpd.studentdashboard.repository;

import com.manhpd.studentdashboard.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
