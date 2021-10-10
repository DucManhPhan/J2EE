package com.manhpd.ecommerce.app.service;

import com.manhpd.ecommerce.persist.entity.Customer;
import com.manhpd.ecommerce.persist.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.timeout;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AnalyticsServiceTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @SpyBean
//    @MockBean
    private AnalyticsService analyticsService;

    @Test
    void registerCustomer_forNewCustomer_callsAnalytics() {
        //given
        Customer customer = new Customer("john@email.com");
        this.customerRepository.save(customer);

        //when
        this.customerService.register(customer);

        //then
        then(analyticsService).should(timeout(100)).registerNewCustomer(customer);
    }

}