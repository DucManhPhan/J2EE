package com.manhpd;

import com.manhpd.service.StartupService;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;


public class App {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();

        // disable discovery and register bean classes manually
        try (SeContainer container = initializer.disableDiscovery().addBeanClasses(StartupService.class).initialize()) {
            container.select(StartupService.class).get().sayHello();
        }
    }
}
