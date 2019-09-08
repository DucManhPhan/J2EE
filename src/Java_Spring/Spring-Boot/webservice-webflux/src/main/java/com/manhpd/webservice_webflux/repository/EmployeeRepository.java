package com.manhpd.webservice_webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.manhpd.webservice_webflux.model.Employee;
import com.manhpd.webservice_webflux.model.Tweet;

public interface EmployeeRepository extends ReactiveMongoRepository<Employee, String> {

}
