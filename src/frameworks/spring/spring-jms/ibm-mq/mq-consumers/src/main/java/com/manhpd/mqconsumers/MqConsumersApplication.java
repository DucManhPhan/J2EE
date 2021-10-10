package com.manhpd.mqconsumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableJms
public class MqConsumersApplication {

    @Autowired
    private JmsTemplate jmsTemplate;

    private String queueName = "QA.VP.ESB.ENT.TXN.ROUTER.REQ";

    public static void main(String[] args) {
        SpringApplication.run(MqConsumersApplication.class, args);
    }

    @GetMapping("send")
    String send(){
        try{
            this.jmsTemplate.convertAndSend(this.queueName, "Hello World!");
            return "OK";
        }catch(JmsException ex){
            ex.printStackTrace();
            return "FAIL";
        }
    }

    @GetMapping("recv")
    String recv(){
        try{
            return this.jmsTemplate.receiveAndConvert(this.queueName).toString();
        }catch(JmsException ex){
            ex.printStackTrace();
            return "FAIL";
        }
    }

}
