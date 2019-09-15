package com.manhpd.webclient_utils.controller;

import org.reactivestreams.Publisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.core.publisher.Mono;

@Controller
public class DataController {

	@GetMapping(value = "/")
	@ResponseBody
	public Publisher<String> getHelloWorld() {
		return Mono.just("Hello world!");
	}
}
