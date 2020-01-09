package netbeans.jax.ws.cdi.webservice;


import javax.inject.Inject;
import javax.jws.WebService;
import netbeans.jax.ws.cdi.service.TmpService;
import org.apache.log4j.Logger;

@WebService(name = "WsReceiver", targetNamespace = "http://tempuri.org/")
public class WsReceiver implements IWsReceiver {

    @Inject
    private Logger logger;
    
    @Inject
    private TmpService tmpService;
    
    @Override
    public double c2f(double degrees) {
        System.out.println("Address of logger field is: " + this.logger);
        this.tmpService.displayData();
        return degrees * 9.0 / 5.0 + 32;

    }

    @Override
    public double cm2in(double cm) {
        return cm / 2.54;
    }

    @Override
    public double f2c(double degrees) {
        return (degrees - 32) * 5.0 / 9.0;
    }

    @Override
    public double in2cm(double in) {
        return in * 2.54;
    }

}
