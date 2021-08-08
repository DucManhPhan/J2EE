package com.manhpd.connectibmmq.ws;

import com.ibm.mq.jms.MQQueue;
import com.manhpd.connectibmmq.application.dto.OrderRequest;
import com.manhpd.connectibmmq.utils.Constant;
import com.manhpd.connectibmmq.utils.ConverterUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Slf4j
@RequestMapping("orders")
@RestController
public class OrderController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest order) throws JMSException {
        MQQueue orderRequestQueue = new MQQueue(Constant.ORDER_REQUEST);
        this.jmsTemplate.convertAndSend(orderRequestQueue, order.getMessage(), textMessage -> {
            textMessage.setJMSCorrelationID(order.getIdentifier());
            return textMessage;
        });

        return new ResponseEntity(order, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<OrderRequest> findOrderByCorrelationId(@RequestParam String correlationId) throws JMSException {
        String convertedId = ConverterUtils.bytesToHex(correlationId.getBytes());
        final String selectorExpression = String.format("JMSCorrelationID = 'ID: %s'", convertedId);
        final TextMessage responseMessage = (TextMessage) this.jmsTemplate.receiveSelected(Constant.ORDER_REQUEST, null);

        OrderRequest response = OrderRequest.builder()
                .message(responseMessage.getText())
                .identifier(correlationId)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
