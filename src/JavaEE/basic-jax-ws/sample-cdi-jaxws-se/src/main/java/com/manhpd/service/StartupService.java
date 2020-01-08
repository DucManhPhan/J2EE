package com.manhpd.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class StartupService {

    @Inject
    private MyService myService;

    public void sayHello() {
        myService.sayHello("world");
    }
}
