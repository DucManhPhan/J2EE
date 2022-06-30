package com.manhpd.contactmanagement.service;

import com.manhpd.contactmanagement.entity.CustomerContact;
import com.manhpd.contactmanagement.repository.CustomerContactRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ContactsManagementServiceUnitTest {

    @Mock
    private CustomerContactRepository customerContactRepository;

    @InjectMocks
    private ContactsManagementService contactsManagementService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddContactHappyPath() {
        // Create a contact
        CustomerContact aMockContact = new CustomerContact();
        aMockContact.setFirstName("Jenny");
        aMockContact.setLastName("Johnson");

        Mockito.when(this.customerContactRepository.save(any(CustomerContact.class)))
               .thenReturn(aMockContact);

        CustomerContact newContact = this.contactsManagementService.add(null);

        // verify the save() method
        Assert.assertEquals("Jenny", newContact.getFirstName());
    }

}
