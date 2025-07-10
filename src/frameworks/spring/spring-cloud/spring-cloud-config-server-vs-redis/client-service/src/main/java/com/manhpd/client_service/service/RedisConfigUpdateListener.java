package com.manhpd.client_service.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
@AllArgsConstructor
public class RedisConfigUpdateListener implements MessageListener {

    private final RestTemplate restTemplate;
    private final RefreshEndpoint refreshEndpoint;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String payload = message.toString();
        if ("refresh".equals(payload)) {
            // Call /actuator/refresh locally
            try {
                // 1st way
                this.restTemplate.postForObject("http://localhost:8082/client-service/actuator/refresh", null, String.class);

                // 2nd way
//                this.refreshEndpoint.refresh();

                System.out.println("[" + "client-service" + "] Config refreshed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
