package com.manhpd.ecommerce.app.listeners;

import com.manhpd.ecommerce.app.events.CustomerRegisteredEvent;
import com.manhpd.ecommerce.app.events.CustomerRemovedEvent;
import com.manhpd.ecommerce.app.events.OrderCompletedEvent;
import com.manhpd.ecommerce.app.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class EmailListeners {

    private final EmailService emailService;

    @EventListener
    public void onRegisteredEvent(CustomerRegisteredEvent event) {
        this.emailService.sendRegisterEmail(event.getCustomer());
    }

    @EventListener
    public void onRemovedEvent(CustomerRemovedEvent event) {
        this.emailService.sendCustomerRemovedEmail(event.getCustomer());
    }

    @EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onOrderCompletedEvent(OrderCompletedEvent event) {
        this.emailService.sendOrderEmail(event.getOrder());
    }

}
