package netbeans.jax.ws.cdi;


import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;
import netbeans.jax.ws.cdi.service.SimpleEvent;
import netbeans.jax.ws.cdi.service.StartupService;
import netbeans.jax.ws.cdi.service.TmpService;
import netbeans.jax.ws.cdi.webservice.WsReceiver;
import org.apache.log4j.Logger;

public class CoreModule {

    @Inject
    private Logger logger;
    
    @Inject
    private TmpService tmpService;
    
    public void onEvent(@Observes SimpleEvent ignored, StartupService startupService) {
        logger.info("Raise events in CoreModule");
        this.tmpService.displayData();
        startupService.sayHello();

        WsReceiver receiver = new WsReceiver();
        Endpoint service = Endpoint.publish("http://localhost:9901/wsreceiver", receiver);
    }

}
