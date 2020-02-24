package com.manhpd.controller;


import com.manhpd.domain.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private IPersonService personService;

    @GetMapping(value = "/persons")
    public String getPersons() {
        return "";
    }

}
