package com.manhpd.predicate;

import java.util.function.Predicate;

/**
 * In java.util.function package contains some basic models: 
 * - Predicate: Tests argument and returns true or false. 
 * - Function: Maps one type to another. 
 * - Consumer: Consumes input (returns nothing).
 * - Supplier: Generates output (using no input).
 * 
 * Predicate is a functional interface whose functional method, called test(),
 * evaluates a condition on an input variable of a generic type. The test()
 * method returns true if the condition is true, and false otherwise.
 * 
 * @FunctionalInterface
 * public interface Predicate<T> {
 * 		boolean test(T t);
 *		
 *		// some static and default methods
 *		// ...
 * }
 */
public class Introduce_Predicate {

	public static void main(String[] args) {
		Predicate<Integer> p1 = x -> x != null;
		System.out.println("Test different null with Predicate: " + p1.test(null));

		Predicate<Integer> isGreaterThan = x -> {
			return x > 5;
		};
		System.out.println("Test something that is greater than 5: " + isGreaterThan.test(4));
	}
}
