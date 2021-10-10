package com.manhpd.ecommerce.app.service;

import com.manhpd.ecommerce.persist.entity.Customer;
import com.manhpd.ecommerce.persist.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EmailCustomerServiceTest
{
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @SpyBean
    private EmailService emailService;

    @Test
    void registerCustomer_forNewCustomer_sendsAnEmail(){

        //given
        Customer customer = new Customer("john@email.com");

        //when
        customerService.register(customer);

        //then
        then(emailService).should(times(1)).sendRegisterEmail(customer);
    }

    @Test
    void removeCustomer_forExistingCustomer_sendsAnEmail(){

        //given
        Customer customer = new Customer("john@email.com");
        customerRepository.save(customer);

        //when
        customerService.remove(customer);

        //then
        then(emailService).should(times(1)).sendCustomerRemovedEmail(customer);
    }

}