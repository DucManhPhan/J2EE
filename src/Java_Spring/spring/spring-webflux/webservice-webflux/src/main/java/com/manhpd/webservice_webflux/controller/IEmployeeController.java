package com.manhpd.webservice_webflux.controller;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.manhpd.webservice_webflux.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEmployeeController {

	@GetMapping(value = { "${employees.all-employees}" })
	default Flux<Employee> getEmployeesAPI(ServerHttpRequest request) {
		return this.getEmployees(request);
	}

	Flux<Employee> getEmployees(ServerHttpRequest request);

	@GetMapping(value = { "${employees.detail}" })
	default Mono<Employee> findEmployeeByIdAPI(ServerHttpRequest request, @PathVariable String id) {
		return this.findEmployeeById(request, id);
	}

	Mono<Employee> findEmployeeById(ServerHttpRequest request, String id);

}
