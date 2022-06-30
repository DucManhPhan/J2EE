package com.manhpd.contactmanagement.controller;

import com.manhpd.contactmanagement.entity.CustomerContact;
import com.manhpd.contactmanagement.service.ContactsManagementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactsManagementControllerIntegrationTest {

    @Autowired
    private ContactsManagementService contactsManagementService;

    @Test
    public void testAddContactHappyPath() {
        CustomerContact aContact = new CustomerContact();
        aContact.setFirstName("Jenny");
        aContact.setLastName("Johnson");

        // POST our CustomerContact from bean to the controller; check the outcome

        // Assert that the outcome is as expected
    }

    @Test
    public void testAddContactFirstNameMissing() {
        CustomerContact aContact = new CustomerContact();
    }

}
