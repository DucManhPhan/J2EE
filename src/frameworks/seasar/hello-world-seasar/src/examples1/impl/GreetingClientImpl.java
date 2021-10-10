package examples1.impl;

public class GreetingClientImpl implements GreetingClient {
	
	private Greeting greeting;
	
	public void setGreeting(Greeting greeting) {
		this.greeting = greeting;
	}
	
	@Override
	public void execute() {
		System.out.println(greeting.greet());
	}
	
}
