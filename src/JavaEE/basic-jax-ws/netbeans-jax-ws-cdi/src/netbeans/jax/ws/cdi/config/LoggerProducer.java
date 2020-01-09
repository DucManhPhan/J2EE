package netbeans.jax.ws.cdi.config;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import org.apache.log4j.Logger;

/**
 *
 * @author ManhPD5
 */
public class LoggerProducer {
    
    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {  
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());  
    }
    
}
