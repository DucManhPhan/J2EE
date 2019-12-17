package sample.jaxws;


import javax.jws.WebService;

/**
 *
 * @author ManhPD5
 */
@WebService(endpointInterface = "sample.jaxws.IHelloWorld")
public class HelloWorldImpl implements IHelloWorld{

    @Override
    public String getHelloWorldAsString(String name) {
        return "Hello world with JAX-WS " + name;
    }

}
