package com.manhpd.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
@ComponentScan(basePackages = {"com.manhpd"})
@PropertySource(value = "classpath:config.properties", ignoreResourceNotFound = true)
public class RestTemplateConfig {

    @Value("${timeout.create-request.ms}")
    private int requestConnectionTimeout;

    @Value("${timeout.connection.ms}")
    private int connectionTimeout;

    @Value("${timeout.socket.ms}")
    private int socketTimeout;

    @Bean
    @Qualifier("mm-resttemplate")
    public RestTemplate restTemplate() {
//        return new RestTemplateBuilder()
//                .additionalMessageConverters(httpMessageConverter())
//                .errorHandler(new RestTemplateResponseErrorHandler())
//                .requestFactory(() -> clientHttpRequestFactory())
//                .build();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(httpMessageConverter());
//        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        restTemplate.setRequestFactory(clientHttpRequestFactory());

        return restTemplate;
    }

    @Bean
    protected HttpComponentsClientHttpRequestFactory clientHttpRequestFactory()
    {
//        logger.info("Info of timeout - resttemplate: " + " request-connection-timeout: " + this.requestConnectionTimeout
//                + ", connection-timeout: " + this.connectionTimeout + ", socket-timeout: " + this.socketTimeout);
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectionRequestTimeout(this.requestConnectionTimeout);
        clientHttpRequestFactory.setConnectTimeout(this.connectionTimeout);
        clientHttpRequestFactory.setReadTimeout(this.socketTimeout);

        return clientHttpRequestFactory;
    }

    @Bean
    protected HttpMessageConverter httpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));

        return converter;
    }

}
