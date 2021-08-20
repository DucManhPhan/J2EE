package com.manhpd.ecommerce.app.service;

import com.manhpd.ecommerce.app.events.CustomerRegisteredEvent;
import com.manhpd.ecommerce.persist.entity.Customer;
import com.manhpd.ecommerce.persist.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final ApplicationEventPublisher publisher;

    public void register(Customer customer) {
        this.customerRepository.save(customer);
        this.publisher.publishEvent(new CustomerRegisteredEvent(customer));
    }

    public void remove(Customer customer) {
        this.customerRepository.delete(customer);
    }
}
