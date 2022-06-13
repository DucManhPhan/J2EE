package com.manhpd.secondservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
public class SecondController {

    @GetMapping("/message")
    public String helloWorld() {
        return "Hello world from Second Service";
    }

}
