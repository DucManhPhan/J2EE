package com.manhpd.ecommerce.ws;

import com.manhpd.ecommerce.app.service.OrderService;
import com.manhpd.ecommerce.persist.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> placeOrder(Order order) {
        Objects.requireNonNull(order);
        this.orderService.placeOrder(order);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                             .build();
    }
}
