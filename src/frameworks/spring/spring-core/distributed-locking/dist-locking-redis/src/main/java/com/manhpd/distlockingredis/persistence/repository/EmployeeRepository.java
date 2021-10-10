package com.manhpd.distlockingredis.persistence.repository;

import com.manhpd.distlockingredis.persistence.entity.MpEmployee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  EmployeeRepository extends CrudRepository<MpEmployee, Long> {
}
