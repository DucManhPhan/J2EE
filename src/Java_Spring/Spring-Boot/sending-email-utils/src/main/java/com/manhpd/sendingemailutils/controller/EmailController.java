package com.manhpd.sendingemailutils.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @GetMapping(value = "/sendemail")
    public String sendEmail() {
        return "Email sent successfully";
    }

}
