package netbeans.jax.ws.cdi;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.xml.ws.Endpoint;
import netbeans.jax.ws.cdi.service.SimpleEvent;
import netbeans.jax.ws.cdi.webservice.WsReceiver;

/**
 *
 * @author ManhPD5
 */
public class NetbeansJaxWsCdi {

    public static void main(String[] args) {
        WsReceiver receiver = new WsReceiver();
        Endpoint service = Endpoint.publish("http://localhost:9901/wsreceiver", receiver);

        // Scan beans in our project
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        SeContainer container = initializer.addPackages(Package.getPackage("netbeans.jax.ws.cdi")).initialize();
        container.getBeanManager().fireEvent(new SimpleEvent());
        
        container.close();
    }
    
}
