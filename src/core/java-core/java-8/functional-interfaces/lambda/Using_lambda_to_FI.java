package com.manhpd.lambda;

public class Using_lambda_to_FI {

	public static void main(String[] args) {

		StringProcessor_Lambda sp = x -> x;
		System.out.println("The content of this Functional Interface is: " + sp.process("Hello, Functional Interface!"));
		
	}

}

/**
 * Lambda expressions are used to represent functional interfaces.
 * 
 */
@FunctionalInterface
interface StringProcessor_Lambda {
	String process(String x);
}