package com.manhpd.ecommerce.app.listeners;

import com.manhpd.ecommerce.app.events.CustomerRegisteredEvent;
import com.manhpd.ecommerce.app.events.OrderCompletedEvent;
import com.manhpd.ecommerce.app.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PromotionListener {

    private final PromotionService promotionService;

    @EventListener(condition = "#event.customer.newsletter")
    public void onRegistrationEvent(CustomerRegisteredEvent event) {
        this.promotionService.applyPromotion(event.getCustomer());
    }

    @EventListener
    public void onOrderCompleted(OrderCompletedEvent event) {
        this.promotionService.calculateRewardPoints(event.getOrder());
    }

}
