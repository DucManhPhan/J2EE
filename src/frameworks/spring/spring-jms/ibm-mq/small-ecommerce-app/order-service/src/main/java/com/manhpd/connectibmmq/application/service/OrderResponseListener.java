package com.manhpd.connectibmmq.application.service;

import com.manhpd.connectibmmq.utils.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Slf4j
@Component
public class OrderResponseListener {

    @JmsListener(destination = Constant.ORDER_RESPONSE)
    public void receive(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        log.info("### 4 ### OrderService received response message: {} with correlationId: {}", textMessage.getText(),
                textMessage.getJMSCorrelationID());

        // do something
    }

}
