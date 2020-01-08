package com.manhpd.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;

@Dependent
public class MyServiceImpl implements MyService {

    @PostConstruct
    public void initialize() {
        System.out.println("Initializing");
    }

    @PreDestroy
    public void cleanup() {
        System.out.println("Cleaning");
    }

    @Override
    public void sayHello(String username) {
        System.out.println("Hello " + username + " from " + MyServiceImpl.class.getName());
    }
}
