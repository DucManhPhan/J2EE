package sample.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;


/**
 *
 * @author ManhPD5
 */
//Service Endpoint Interface
@WebService
@SOAPBinding(style = Style.RPC)
public interface IHelloWorld {
    
    @WebMethod
    String getHelloWorldAsString(String name);
    
}
