package com.manhpd.contactmanagement.controller;

import com.manhpd.contactmanagement.entity.ContactImportantOccasion;
import com.manhpd.contactmanagement.entity.CustomerContact;
import com.manhpd.contactmanagement.service.ContactsManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@RestController
@Controller
public class ContactsManagementController {

    @Autowired
    private ContactsManagementService contactsManagementService;

    @PostMapping("/addContact")
    public String processAddContactSubmit(@ModelAttribute CustomerContact aContact) {
        CustomerContact newContact = this.contactsManagementService.add(aContact);
        if (newContact != null) {
            return "/addContactForm";
        }

        return "redirect:/showAddContact";
    }

    @GetMapping("/showAddContact")
    public String showAddContact() {
        // implement this

        return "/addContactForm";
    }

    public String processAddContactOccasionSubmit(@ModelAttribute CustomerContact aContact, @ModelAttribute ContactImportantOccasion anOccation) {
        // implement this

        return "/addContactOccasionForm";
    }

}
