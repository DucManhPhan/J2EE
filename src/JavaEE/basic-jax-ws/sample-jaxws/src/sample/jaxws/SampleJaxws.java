package sample.jaxws;

import javax.xml.ws.Endpoint;

/**
 *
 * @author ManhPD5
 */
public class SampleJaxws {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ws/hello", new HelloWorldImpl());
    }

}