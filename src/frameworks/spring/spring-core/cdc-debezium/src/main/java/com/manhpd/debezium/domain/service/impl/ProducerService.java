package com.manhpd.debezium.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
public class ProducerService {

   @Value("${kafka.topic.first-topic}")
   private String firstTopic;

   @Autowired
   private KafkaTemplate<String, String> kafkaTemplate;

   public void sendMessage(String message) {
      ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(firstTopic, message);

      future.addCallback((SendResult<String, String> result) -> {
         System.out.println("Sent message=[" + message +
                 "] with offset=[" + result.getRecordMetadata().offset() + "]");
      }, (Throwable ex) -> {
         System.out.println("Unable to send message=["
                 + message + "] due to : " + ex.getMessage());
      });
   }

}
