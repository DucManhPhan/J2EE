package netbeans.jax.ws.cdi;


import javax.enterprise.event.Observes;
import javax.inject.Inject;
import netbeans.jax.ws.cdi.service.SimpleEvent;
import netbeans.jax.ws.cdi.service.StartupService;
import netbeans.jax.ws.cdi.service.TmpService;
import org.apache.log4j.Logger;

public class CoreModule {

    @Inject
    private Logger logger;
    
    @Inject
    private TmpService tmpService;
    
    public void onEvent(@Observes SimpleEvent ignored, StartupService service) {
        logger.info("Raise events in CoreModule");
        this.tmpService.displayData();
        service.sayHello();
    }

}
