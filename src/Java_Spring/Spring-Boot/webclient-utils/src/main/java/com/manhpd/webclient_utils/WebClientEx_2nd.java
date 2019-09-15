package com.manhpd.webclient_utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.manhpd.webclient_utils.model.User;

import reactor.core.publisher.Mono;


/**
 * https://stackoverflow.com/questions/44593066/spring-webflux-webclient-get-body-on-error
 * 
 *
 */
public class WebClientEx_2nd {

	public static void main(String[] args) {
		String uriLogin = "/users/auth";
		post(uriLogin, null);
	}
	
	public static void post(String path, Object obj) {
		WebClient webClient = WebClientEx.createWebClient();

		User user = new User("admin", "admin");
		Mono<String> resultIronMan = webClient.post()
											.uri(path)
											.contentType(MediaType.APPLICATION_JSON)
											.syncBody(user)
											.retrieve()
											.onStatus(HttpStatus::is4xxClientError, (ClientResponse clientResponse) -> {
												throw new WebClientResponseException("Unauthorized", 0, null, null, null, null);
											})
											.onStatus(HttpStatus::is5xxServerError, (ClientResponse clientResponse) -> {
												System.out.println(clientResponse);
												throw new WebClientResponseException("Internal Server Error", 0, null, null, null, null);
											})
											.bodyToMono(String.class);
//		String res = resultIronMan.block();
//		System.out.println(res);
		resultIronMan.subscribe(res -> {
			System.out.println(res);
		});
	}
}
