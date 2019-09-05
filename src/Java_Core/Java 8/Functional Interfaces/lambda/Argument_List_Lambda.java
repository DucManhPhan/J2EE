package com.manhpd.lambda;

/**
 * If argument list of lambda contains a single argument and the type of the
 * argument can be inferred, the argument may be specified without parentheses.
 * A type may be provided for the argument, but then the argument list of lambda
 * must be enclosed in parentheses.
 * Ex: (String x) -> x
 * 
 * If the argument list of lambda contains several arguments, each must be
 * separated by a comma, and the list must be enclosed by parentheses. The
 * argument types do not need to be specified if they can be inferred by the
 * complier.
 * Ex: (x, y) -> x + y
 * 
 * If the argument list of lambda contains no arguments, it must be specified by an empty set of parentheses.
 * Ex: () -> 10
 */
public class Argument_List_Lambda {

	static <T> void m2(Z<T> arg) {
		// nothing to do
	}

	public static void main(String[] args) {
//		m2(x -> x.i); // Error

		m2((A x) -> x.i);
		
		FIArgumentList argumentList = (a, b) -> System.out.println("The value of sum of two number is: " + a + b);
		argumentList.print(3, 5);
	}

}

class A {
	int i;
}

@FunctionalInterface
interface Z<T> {
	int m(T t);
}

@FunctionalInterface
interface FIArgumentList {
	void print(int i, int j);
}