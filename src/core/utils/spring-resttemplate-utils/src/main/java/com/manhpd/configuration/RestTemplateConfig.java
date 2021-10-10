package com.manhpd.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.manhpd.shared.Constant;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@ComponentScan(basePackages = {"com.manhpd"})
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        // 1st way - only use MappingJackson2HttpMessageConverter
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        restTemplate.getMessageConverters().add(converter);

        // 2nd - use list of MessageConverter
        // Configure ClientHttpRequestFactory class
        // 1st way - use RequestConfig class of Apache HttpClient to deal with it
//        RequestConfig config = RequestConfig.custom()
//                .setConnectTimeout(Constant.REQUEST_TIME_OUT)
//                .setConnectionRequestTimeout(Constant.REQUEST_TIME_OUT)
//                .setSocketTimeout(Constant.REQUEST_TIME_OUT)
//                .build();
//        CloseableHttpClient client = HttpClientBuilder
//                .create()
//                .setDefaultRequestConfig(config)
//                .build();
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(client);

        // 2nd way - call directly method of ClientHttpRequestFactory class
        // such as setConnectTimeout(), setConnectionRequestTimeout(), setReadTimeout()
//        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//        requestFactory.setConnectTimeout(Constant.REQUEST_TIME_OUT);

//        RestTemplate restTemplate = new RestTemplate(requestFactory);
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//        messageConverters.add(new FormHttpMessageConverter());
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(new ObjectMapper());
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        messageConverters.add(converter);
//        restTemplate.setMessageConverters(messageConverters);

        return restTemplate;
    }

}
