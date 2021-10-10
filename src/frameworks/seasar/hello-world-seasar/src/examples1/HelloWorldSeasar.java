package examples1;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;

import examples1.impl.GreetingClient;

public class HelloWorldSeasar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String configure_path = "examples1/dicon/examples1.dicon";
		
		try {
			S2Container container = S2ContainerFactory.create(configure_path);
			container.init();	// add to initialize components
            try {
              GreetingClient greetingClient = (GreetingClient)container.getComponent("greetingClient");
              greetingClient.execute();
          } finally {
            container.destroy();
          }
      } catch (ResourceNotFoundRuntimeException e){
        System.out.println("Configuration file \"" + configure_path + "\" not found.");
      }
	}

}
