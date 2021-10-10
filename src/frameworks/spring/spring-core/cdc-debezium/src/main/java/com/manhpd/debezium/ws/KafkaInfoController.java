package com.manhpd.debezium.ws;

import com.manhpd.debezium.domain.service.impl.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaInfoController {

    private final ProducerService producerService;

    @Autowired
    public KafkaInfoController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping(value = "/publish")
    public void sendMessageKafkaTopic(@RequestParam("message") String message) {
        this.producerService.sendMessage(message);
    }

}
