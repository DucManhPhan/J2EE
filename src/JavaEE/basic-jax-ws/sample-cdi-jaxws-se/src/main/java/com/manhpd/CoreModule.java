package com.manhpd;

import com.manhpd.service.SimpleEvent;
import com.manhpd.service.StartupService;

import javax.enterprise.event.Observes;

public class CoreModule {

    public void onEvent(@Observes SimpleEvent ignored, StartupService service) {
        service.sayHello();
    }

}
