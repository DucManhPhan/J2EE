package com.manhpd.webservice_webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.manhpd.webservice_webflux.model.Employee;
import com.manhpd.webservice_webflux.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@PropertySource(value = "classpath:/application-webservice.properties", ignoreResourceNotFound = true)
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping(value = { "${employees.all-employees}" })
	public Flux<Employee> getEmployees() {
		return employeeRepository.findAll();
	}
	
	@GetMapping(value = { "${employees.detail}" })
	public Mono<Employee> getById(@PathVariable String id) {
		return employeeRepository.findById(id);
	}
}
