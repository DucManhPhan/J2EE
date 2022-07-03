package com.manhpd.contactmanagement.service;

import com.manhpd.contactmanagement.entity.ContactImportantOccasion;
import com.manhpd.contactmanagement.entity.CustomerContact;
import com.manhpd.contactmanagement.repository.CustomerContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactsManagementService {

    @Autowired
    private CustomerContactRepository customerContactRepository;

    public CustomerContact add(CustomerContact aContact) {
        CustomerContact newContact = null;

        if (aContact.getFirstName() != null) {
            newContact = this.customerContactRepository.save(aContact);
        }

        return newContact;
    }

    public CustomerContact addContactOccasion(CustomerContact aContact, ContactImportantOccasion anOccasion) {
        CustomerContact newContact = null;
        return newContact;
    }

}
