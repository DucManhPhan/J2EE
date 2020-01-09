package netbeans.jax.ws.cdi.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.apache.log4j.Logger;

@ApplicationScoped
public class StartupService {

    @Inject
    private Logger logger;
    
    @Inject
    private MyService myService;

    public void sayHello() {
        logger.info("Step into sayHello() method: ");
        myService.sayHello("world");
    }
}
