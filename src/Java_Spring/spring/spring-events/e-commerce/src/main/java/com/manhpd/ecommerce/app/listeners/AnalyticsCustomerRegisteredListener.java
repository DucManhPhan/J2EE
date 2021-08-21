package com.manhpd.ecommerce.app.listeners;

import com.manhpd.ecommerce.app.events.CustomerRegisteredEvent;
import com.manhpd.ecommerce.app.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnalyticsCustomerRegisteredListener {

    private final AnalyticsService analyticsService;

    @Async
    @EventListener
    public void onRegisteredEvent(CustomerRegisteredEvent event) {
        this.analyticsService.registerNewCustomer(event.getCustomer());
    }

}
