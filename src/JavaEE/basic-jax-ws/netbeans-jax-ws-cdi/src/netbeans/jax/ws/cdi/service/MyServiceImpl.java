package netbeans.jax.ws.cdi.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;

//@Dependent
@Singleton
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
