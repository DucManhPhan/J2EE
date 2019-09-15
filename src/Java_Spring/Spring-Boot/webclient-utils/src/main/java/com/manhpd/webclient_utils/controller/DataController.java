package com.manhpd.webclient_utils.controller;

import org.reactivestreams.Publisher;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manhpd.webclient_utils.model.Employee;

import reactor.core.publisher.Mono;

@Controller
public class DataController {

	@GetMapping(value = "/")
	@ResponseBody
	public Publisher<String> getHelloWorld() {
		return Mono.just("Hello world!");
	}

	@PostMapping(value = "/students")
	@ResponseBody
	public Mono<String> createStudent(ServerHttpRequest request, @RequestBody Object obj) {
		Employee emp = (Employee) obj;
		System.out.println(emp.toString());

		return Mono.just("That's it.");
	}
}
