package com.manhpd.debezium.domain.service.impl;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @KafkaListener(topics = "", groupId = "")
    public void consume(String message) {
        System.out.println(String.format("#### -> Consumed message -> %s", message));
    }

}
