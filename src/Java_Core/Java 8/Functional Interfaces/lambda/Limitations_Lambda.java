package com.manhpd.lambda;

/**
 * The code in a lambda expression becomes the functional method of the
 * functional interface it implements. Therefore, lambdas can not be used to
 * override a functional interface's default method. If overriding the default
 * methods required, a named or anonymous class must be used.
 * 
 * It is not possible to call default method from inside the lambda expression.
 * This is because the lambda expression is just simple code that has no idea it
 * is part of an implementation of functional interface. So calling an instance
 * method is not possible.
 * 
 * Lambda expression can be used to represent implementations of functional
 * interfaces that contain static methods as well.
 * 
 * Lambda expressions can not be used to represent abstract classes that specialize generic
 * functional interfaces. Because the abstract classes are inherited from generic functional interfaces,
 * so, they are not functional interfaces.
 * 
 */
public class Limitations_Lambda {

	public static void main(String[] args) {

		printRandomNumber cmd = () -> {
			// System.out.println(generateRandomNumber()); // ERROR: generateRandomNumber()
			// method do not releasize by lambda expression.
			System.out.println((int) Math.random() * 10 + 1);
		};

		cmd.print();

		// OR
		System.out.println(cmd.generateRandomNumber());

	}
}

@FunctionalInterface
interface printRandomNumber {
	void print();

	default int generateRandomNumber() {
		return (int) Math.random() * 10 + 1;
	}
}