package com.manhpd.webclient_utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.manhpd.webclient_utils.model.User;

import reactor.core.publisher.Mono;
import reactor.spring.factory.CreateOrReuseFactoryBean;

public class WebClientEx {

	private static final String BASE_URL = "http://dev.travelcells.com.vn:9999";

	public static void main(String[] args) throws InterruptedException {
		
		// authentication
		String uriLogin = "/users/auth";
//		String data = "{username:admin, password:admin}";
		User user = new User("admin", "admin");
		post(uriLogin, user);
		
//		String path = "/nslcm/v1/ns_instances";
//		get(path);
	}

	private static WebClient createWebClient() {
		return WebClient.builder()
				.baseUrl(BASE_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				.build();
	}

	private static void get(String path) throws InterruptedException {
		// Use blocking to wait for response
		WebClient webClient = createWebClient();
		Mono<String> result = webClient.get()
										.uri(path)
										.retrieve()
										.bodyToMono(String.class);
		String response = result.block();
		System.out.println(response);
		
		// Use non-blocking
//		Mono<String> result_response = webClient.get()
//												.retrieve()
//												.bodyToMono(String.class);
//		result.subscribe(WebClientEx::handleResponse);
//		System.out.println("After subscribe");
//		Thread.sleep(1000);
	}

	private static void post(String path, Object data) {
		WebClient webClient = createWebClient();
		Mono<String> result = webClient.post()
										.uri(path)
										.body(BodyInserters.fromObject(data))
										.retrieve()
										.bodyToMono(String.class)
										.log();
		
		String response = result.block();
		System.out.println(response);
	}
	
	private static void handleResponse(String s) {
		System.out.println("handle response");
		System.out.println(s);
	}
}
