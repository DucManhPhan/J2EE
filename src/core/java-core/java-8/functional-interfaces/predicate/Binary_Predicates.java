package com.manhpd.predicate;

import java.util.function.BiPredicate;

/**
 * The BiPredicate interface specifies two type parameters.
 * Ex:
 * @FunctionalInterface
 * public interface BiPredicate<T, U>s {
 * 		boolean test(T t, U u);
 * 		
 * 		// other default methods
 * 		...
 * }
 */
public class Binary_Predicates {

	public static void main(String[] args) {

		BiPredicate<String, Integer> bi = (x, y) -> x.equals("Manager") && y > 100000;
		String position = "Manager";
		int salary = 150000;
		System.out.println(bi.test(position, salary));
	}
}
