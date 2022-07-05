package com.manhpd.contactmanagement.controller;

import com.manhpd.contactmanagement.entity.CustomerContact;
import com.manhpd.contactmanagement.service.ContactsManagementService;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactsManagementController.class)
public class ContactsManagementControllerUnitTest {

    private static int REDIRECT_RESOURCE_STATUS = 302;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactsManagementService contactsManagementService;

    @InjectMocks
    private ContactsManagementController contactsManagementController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddContactHappyPath() throws Exception {
        // setup mock Contact returned the mock service component
        CustomerContact mockCustomerContact = new CustomerContact();
        mockCustomerContact.setFirstName("Fred");

        Mockito.when(this.contactsManagementService.add(Matchers.any(CustomerContact.class)))
               .thenReturn(mockCustomerContact);

        // simulate the form bean that would POST from the web page
        CustomerContact aContact = new CustomerContact();
        aContact.setFirstName("Fred");
        aContact.setEmail("fredj@myemail.com");

        // simulate the form submit POST
        this.mockMvc.perform(MockMvcRequestBuilders.post("/addContact", aContact))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
    }

    @Test
    public void testAddContactBizServiceRuleNotSatisfied() throws Exception {
        // setup a mock response of NULL object returned from the mock service component
        Mockito.when(this.contactsManagementService.add(Matchers.any(CustomerContact.class)))
               .thenReturn(null);

        // simulate the form bean that would POST from the web page
        CustomerContact aContact = new CustomerContact();
        aContact.setLastName("Johnson");

        // simulate the form submit (POST)
        this.mockMvc.perform(MockMvcRequestBuilders.post("/addContact", aContact))
                .andExpect(MockMvcResultMatchers.status().is(REDIRECT_RESOURCE_STATUS))
                .andReturn();
    }

    @Test
    public void testAddContactOccasionHappyPath() {
        // nothing to do
    }

}

