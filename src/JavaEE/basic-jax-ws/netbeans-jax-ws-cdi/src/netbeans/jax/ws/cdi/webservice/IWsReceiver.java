package netbeans.jax.ws.cdi.webservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IWsReceiver {

    @WebMethod
    public double c2f(double degrees);

    @WebMethod
    public double cm2in(double cm);

    @WebMethod
    public double f2c(double degrees);

    @WebMethod
    public double in2cm(double in);

}
