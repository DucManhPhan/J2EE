package netbeans.jax.ws.cdi;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.inject.Inject;
import javax.xml.ws.Endpoint;
import netbeans.jax.ws.cdi.service.MyService;
import netbeans.jax.ws.cdi.service.MyServiceImpl;
import netbeans.jax.ws.cdi.service.SimpleEvent;
import netbeans.jax.ws.cdi.service.TmpService;
import netbeans.jax.ws.cdi.webservice.WsReceiver;
import org.apache.log4j.Logger;

/**
 *
 * @author ManhPD5
 */
public class NetbeansJaxWsCdi {

    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        // addPackages(Package.getPackage("netbeans.jax.ws.cdi")).
//        SeContainer container = initializer.initialize();
        SeContainer container = initializer.addPackages(true, CoreModule.class).disableDiscovery().initialize();
//        TmpService tmpService = container.select(TmpService.class).get();
//        tmpService.displayData();
        
//        MyService myService = container.select(MyServiceImpl.class).get();
//        myService.sayHello("Everybody");
        container.getBeanManager().fireEvent(new SimpleEvent());
        
        container.close();
    }
    
}
