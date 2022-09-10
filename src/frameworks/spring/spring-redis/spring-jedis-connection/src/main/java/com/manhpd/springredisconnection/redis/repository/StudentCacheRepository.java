package com.manhpd.springredisconnection.redis.repository;

import com.manhpd.springredisconnection.redis.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCacheRepository extends CrudRepository<Student, String> {
}
