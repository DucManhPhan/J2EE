package com.manhpd.webclient_utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.manhpd.webclient_utils.model.Employee;
import com.manhpd.webclient_utils.model.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.spring.factory.CreateOrReuseFactoryBean;

public class WebClientEx {

	private static final String BASE_URL = "http://dev.travelcells.com.vn:9999";

	public static void main(String[] args) throws InterruptedException {
		
		// authentication
//		String uriLogin = "/users/auth";
//		String data = "{\"username\":\"admin\",\"password\":\"admin\"}";
//		User user = new User("admin", "admin");
//		post(uriLogin, user);
		
		// create employee
		String path = "/students";
		Employee emp = new Employee(1, "Obama", 24, 24.0);
		post(path, emp);
		
//		String path = "/nslcm/v1/ns_instances";
//		get(path);
	}

	public static WebClient createWebClient() {
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
										.accept(MediaType.APPLICATION_JSON)
										.retrieve()
										.bodyToMono(String.class);
		String response = result.block();
		System.out.println(response);

		// Use non-blocking
//		Mono<String> result_response = webClient.get()
//												.uri(path)
//												.accept(MediaType.APPLICATION_JSON)
//												.retrieve()
//												.bodyToMono(String.class);
//		result.subscribe(WebClientEx::handleResponse);
//		System.out.println("After subscribe");
//		Thread.sleep(1000);
		
		// Use retrieve() method and check error 4xx, 5xx
		Mono<String> monoResult = webClient.get()
											.uri(path)
											.accept(MediaType.APPLICATION_JSON)
											.retrieve()
											.onStatus(HttpStatus::is4xxClientError, (ClientResponse clientResponse) -> {
												throw new WebClientResponseException("Bad request", 0, null, null, null, null);
											})
											.onStatus(HttpStatus::is5xxServerError, (ClientResponse clientResponse) -> {
												throw new WebClientResponseException("Internal Server Error", 0, null, null, null, null);
											})
											.bodyToMono(String.class);
		monoResult.subscribe(System.out::println);

		// Use exchange() method to control response
		monoResult = (Mono<String>) webClient.get()
											 .uri(path)
											 .accept(MediaType.APPLICATION_JSON)
											 .exchange()
											 .flatMap(res -> res.bodyToMono(String.class));

		Mono<ResponseEntity<String>> resultResponseEntity = (Mono<ResponseEntity<String>>) webClient.get()
																									.uri(path)
																									.accept(MediaType.APPLICATION_JSON)
																									.exchange()
																									.flatMap(res -> res.toEntity(String.class));
		resultResponseEntity.subscribe(System.out::println);

		// fill request header with GET request
		// https://stackoverflow.com/questions/50223891/how-to-extract-response-header-status-code-from-spring-5-webclient-clientrespo
		// https://www.callicoder.com/spring-5-reactive-webclient-webtestclient-examples/
		String basicAuth = "";
		// Do something to get basic authentication

		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		// Do something to get values for request headers

		webClient.get()
				.uri(builder -> builder.path(path)
										.queryParams(multiValueMap)
										.build())
				.accept(MediaType.APPLICATION_JSON)
				.header(HttpHeaders.AUTHORIZATION, basicAuth)
				.retrieve()
				.bodyToMono(String.class);
	}

	private static void post(String path, Object data) throws InterruptedException {
		// Use BodyInserters for RequestBody
		WebClient webClient = createWebClient();
		Mono<String> result = webClient.post()
										.uri(path)
										.body(BodyInserters.fromObject(data))
										.retrieve()
										.bodyToMono(String.class);

//		String response = result.block();
		result.subscribe(WebClientEx::handleResponse);
		System.out.println("After subscribe");
		Thread.sleep(1000);

		// Use body() method with Mono<User>
		Mono<User> monoUser = null;
		Mono<Void> resultMono = webClient.post()
									 .uri(path)
									 .contentType(MediaType.APPLICATION_JSON)
									 .body(monoUser, User.class)
									 .retrieve()
									 .bodyToMono(Void.class);

		// Use body() method with Flux<User>
		Flux<User> userFlux = null;
		Mono<Void> resultFlux = webClient.post()
										 .uri(path)
										 .contentType(MediaType.APPLICATION_STREAM_JSON)
										 .body(userFlux, User.class)
										 .retrieve()
										 .bodyToMono(Void.class);

		// Use syncBody() method with specific object User
		User user = new User("IronMan", "12345");
		Mono<Void> resultIronMan = webClient.post()
											.uri(path)
											.contentType(MediaType.APPLICATION_JSON)
											.syncBody(user)
											.retrieve()
											.bodyToMono(Void.class);
		resultIronMan.subscribe(System.out::println);
	}

	public static void delete(String path) {
		WebClient webClient = createWebClient();
		webClient.delete()
				 .uri(path)
				 .exchange()
				 .subscribe();
	}

	public static void put(String path) {
		User user = new User("IronMan", "12345");
		
		WebClient webClient = createWebClient();
		webClient.put()
				 .uri(path)
				 .contentType(MediaType.APPLICATION_JSON)
				 .syncBody(user)
				 .retrieve()
				 .bodyToMono(User.class)
				 .subscribe(System.out::println);
	}

	private static void handleResponse(String s) {
		System.out.println("handle response");
		System.out.println(s);
	}
}
