package com.manhpd.debezium.persistence.repository;

import com.manhpd.debezium.persistence.entity.EmployeeRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRedisRepository extends CrudRepository<EmployeeRedis, String> {
}
