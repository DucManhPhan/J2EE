package com.manhpd.ecommerce.app.service;

import com.manhpd.ecommerce.persist.entity.Customer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AnalyticsService {

    @SneakyThrows
    public void registerNewCustomer(Customer customer) {
        log.info("Calling analytics service for customer {}", customer);
        Thread.sleep(5_000);
    }
}
