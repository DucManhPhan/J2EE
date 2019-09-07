package com.manhpd.predicate;

import java.util.function.Predicate;

/**
 * When a predicate is passed to a method and the Predicate object's test method
 * is called inside the method, whatever condition was associated with the
 * predicate will be executed. 
 * Therefore, if several Predicate objects are
 * defined, the same logic can be applied to each condition by passing each
 * predicate to a method.
 * 
 *
 */
public class Pass_Predicate_to_Method {

	public static void main(String[] args) {
		Predicate<Integer> isGreaterThan = x -> x > 0;
		result(isGreaterThan, 10);

		// OR
		result(x -> x > 0, 2);
		
	}

	/**
	 * If a reference variable is not provided, Java will attempt to infer the data type
	 * of the Predicate object.
	 * 
	 * @param <T>
	 * @param isGreaterThan
	 * @param arg
	 */
	public static <T> void result(Predicate<T> isGreaterThan, T arg) {
		if (isGreaterThan.test(arg)) {
			System.out.println("Result is satisfied with condition.");
		} else {
			System.out.println("Result is not satisfied with condition.");
		}
	}
}
