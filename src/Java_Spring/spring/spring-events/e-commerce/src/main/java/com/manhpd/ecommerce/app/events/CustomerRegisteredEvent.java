package com.manhpd.ecommerce.app.events;

import com.manhpd.ecommerce.persist.entity.Customer;
import lombok.Data;

@Data
public class CustomerRegisteredEvent {
    private final Customer customer;
}
