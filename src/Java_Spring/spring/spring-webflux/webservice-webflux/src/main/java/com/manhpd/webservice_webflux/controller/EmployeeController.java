package com.manhpd.webservice_webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.RestController;

import com.manhpd.webservice_webflux.model.Employee;
import com.manhpd.webservice_webflux.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@PropertySource(value = "classpath:/application-webservice.properties", ignoreResourceNotFound = true)
public class EmployeeController implements IEmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Flux<Employee> getEmployees(ServerHttpRequest request) {
		return employeeRepository.findAll();
	}

	public Mono<Employee> findEmployeeById(ServerHttpRequest request, String id) {
		return employeeRepository.findById(id);
	}

}
