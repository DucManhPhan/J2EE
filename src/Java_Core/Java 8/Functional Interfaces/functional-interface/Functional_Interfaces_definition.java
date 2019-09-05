package com.manhpd;

/**
 * A functional interface is an interface with a single abstract method, called
 * it functional method.
 * 
 */
public class Functional_Interfaces_definition {

	public static void main(String[] args) {

		NamedStringProcessor namedSP = new NamedStringProcessor();

		/**
		 * 2nd way: A functional interface can be implemented by an anonymous class that
		 * provides the functional method.
		 */
		StringProcessor anonymousSP = new StringProcessor() {
			@Override
			public String process(String x) {
				return x.toUpperCase();
			}
		};

		System.out.println(namedSP.process("hello"));
		System.out.println(anonymousSP.process("hello"));
	}

}

/**
 * If StringProcessor contained more than one abstract method,
 * the @FunctionalInterface annotation would cause a compilation error to be
 * generated. FunctionalInterfaces are ideal for defining a single problem or
 * operation.
 * 
 */
@FunctionalInterface
interface StringProcessor {
	String process(String x);
}

/**
 * 1st way: A functional interface can be implemented by defining a named class
 * that provides the functional method.
 *
 */
class NamedStringProcessor implements StringProcessor {

	@Override
	public String process(String x) {
		return x;
	}

}
