package com.manhpd.ecommerce.ws;

import com.manhpd.ecommerce.app.dto.CustomerDto;
import com.manhpd.ecommerce.app.service.CustomerService;
import com.manhpd.ecommerce.persist.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerDto dto) {
        Objects.requireNonNull(dto);
        this.customerService.register(this.toCustomer(dto));

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                             .build();
    }

    @DeleteMapping
    public ResponseEntity<?> removeCustomer(@RequestBody CustomerDto dto) {
        Objects.requireNonNull(dto);
        this.customerService.remove(this.toCustomer(dto));

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .build();
    }

    private Customer toCustomer(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setEmail(dto.getEmail());
        customer.setRewardPoints(dto.getRewardPoints());
        customer.setNewsletter(dto.isNewsletter());

        return customer;
    }
}
