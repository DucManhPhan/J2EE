package com.manhpd.ecommerce.app.service;

import com.manhpd.ecommerce.persist.entity.Customer;
import com.manhpd.ecommerce.persist.entity.Order;
import com.manhpd.ecommerce.persist.repository.CustomerRepository;
import com.manhpd.ecommerce.persist.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;

import static com.manhpd.ecommerce.persist.entity.Order.OrderStatus.SAVED;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

//@Slf4j
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class EmailOrderServiceTest
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @SpyBean
    private EmailService emailService;

    @Test
    void forPlaceOrder_whenRolledBacked_dontSendEmail()
    {

        //given
        Customer customer = givenCustomer(90);
        Order order = givenOrder(customer);

        //when
        try
        {
            orderService.placeOrder(order);
        }
        catch (Exception e)
        {
//            log.error("Exception while placing an order", e);
            System.out.println("Exception while placing an order: {}" + e.toString());
        }

        //then
        then(emailService).shouldHaveNoInteractions();

    }

    @Test
    void forPlaceOrder_whenCommitSuccessful_sendAnEmail()
    {

        //given
        Customer customer = givenCustomer(50);
        Order order = givenOrder(customer);

        //when
        orderService.placeOrder(order);

        //then
        then(emailService).should(times(1)).sendOrderEmail(order);

    }

    private Order givenOrder(Customer customer)
    {
        Order order = new Order(SAVED);
        order.setCustomer(customer);
        return orderRepository.save(order);
    }

    private Customer givenCustomer(int rewardPoints)
    {
        Customer customer = new Customer("john@email.com");
        customer.setRewardPoints(BigDecimal.valueOf(rewardPoints));
        return customerRepository.save(customer);
    }

}