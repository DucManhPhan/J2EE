package com.manhpd.contactmanagement.controller;

import com.manhpd.contactmanagement.entity.CustomerContact;
import com.manhpd.contactmanagement.service.ContactsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addContact")
public class ContactsManagementController {

    @Autowired
    private ContactsManagementService contactsManagementService;

    @PostMapping
    public String processAddContactSubmit(@RequestBody CustomerContact aContact) {
        CustomerContact newContact = this.contactsManagementService.add(aContact);
        if (newContact != null) {
            return "success";
        }

        return "failure";
    }

}
