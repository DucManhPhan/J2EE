package com.manhpd.vendingmachine.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info().title("Vending machine")
                        .version("1.0.0")
                        .description("Vending machine")
                        .contact(new Contact().name("Vending machine")));
    }

}
