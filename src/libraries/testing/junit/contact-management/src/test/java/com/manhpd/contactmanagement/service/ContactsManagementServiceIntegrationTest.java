package com.manhpd.contactmanagement.service;

import com.manhpd.contactmanagement.entity.CustomerContact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ContactsManagementServiceIntegrationTest {

    @Autowired
    private ContactsManagementService contactsManagementService;

    @Test
    public void testAddContactHappyPath() {
        // Create a contact
        CustomerContact aContact = new CustomerContact();
        aContact.setFirstName("Jenny");
        aContact.setLastName("Johnson");

        // Test adding the contact
        CustomerContact newContact = contactsManagementService.add(aContact);

        // Verify the condition
        Assert.assertNotNull(newContact);
        Assert.assertNotNull(newContact.getId());
        Assert.assertEquals("Jenny", newContact.getFirstName());
    }

}
