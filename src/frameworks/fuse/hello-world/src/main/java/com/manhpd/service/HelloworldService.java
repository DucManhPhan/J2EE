package com.manhpd.service;

import com.manhpd.dto.HelloWorldRequestDto;
import org.apache.camel.Exchange;

import java.util.HashMap;
import java.util.Map;

public class HelloworldService {

    public void helloWorld(Exchange exchange) {
        HelloWorldRequestDto requestDto = (HelloWorldRequestDto) exchange.getProperty("requestDto");
        String name = requestDto.getName();

        // build response
        Map<String, String> response = new HashMap<>();
        response.put("data", "Hello world " + name);
        exchange.getOut().setBody(response);
    }

}
