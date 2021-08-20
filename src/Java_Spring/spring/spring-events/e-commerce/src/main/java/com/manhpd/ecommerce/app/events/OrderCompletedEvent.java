package com.manhpd.ecommerce.app.events;

import com.manhpd.ecommerce.persist.entity.Order;
import lombok.Data;

@Data
public class OrderCompletedEvent {
    private final Order order;
}
