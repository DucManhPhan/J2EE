package com.manhpd.webclient_utils.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

	private static final String BASE_URL = "";

	@Bean
	public WebClient webClient(){
		return WebClient.builder()
						.baseUrl(BASE_URL)
						.build();
	}
	
}
