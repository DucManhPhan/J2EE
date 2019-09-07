package com.manhpd.predicate;

import java.util.function.Predicate;

/**
 * Many of functional interfaces have default methods that return new functional
 * interface objects, whose methods can, in turn, be called down the method
 * chain.
 * 
 * Using this technique, long chains of functional interfaces can be used to
 * perform series of calculations and to inline the logic of your program.
 * 
 * A predicate chain combines the logic performed by two consecutive predicates
 * into a composed predicate. The composed predicate of the first two operations
 * becomes the first operand of the second composed predicate, and so on.
 * Functional method test(), which should be the final link in the chain, is
 * evaluated first, followed by each link of the chain in order.
 * 
 */
public class Chain_Functional_Interfaces {

	public static void main(String[] args) {
		// Evaluate the logic expression x > 7 || x < 3
		// The Predicate interface's default or() method does this by creating a composed
		// predicate whose conditiion is the logical OR of the existing predicate's condition
		// with the condition of its argument.
		// Ex:
		// default Predicate<T> or(Predicate<? super T> other);
		Predicate<Integer> p = x -> x > 7;
		System.out.println(p.or(x -> x < 3)
							.test(9));
		
		result(p.or(x -> x > 3), 9);
		
		// Evaluate the logical expression x > 7 && x%2 == 1
		// The Predicate interface's default and() method accomplishes this by creating
		// a composed predicate whose condition is the logical AND of the existing
		// predicate's condition with the condition of its argument.
		// Ex:
		// default Predicate<T> and(Predicate<? super T> other);
		result(p.and(x -> x%2 == 1), 9);
		
		// The Predicate interface's default negate() method reverses the result of the current predicate.
		// Ex:
		// default Predicate<T> negate();
		System.out.println(p.negate().test(9));
		
		// Evaluate the expression: !((x > 7) && (x%2 == 1))
		System.out.println(p.and(x -> x%2 == 1).negate().test(8));
		
		// The Predicate interface's static isEqual() method uses the equals() method of the Predicate object's
		// type parameter to check if the argument to the test() method is equal to a value.
		// Ex:
		// static <T> Predicate<T> isEqual(Object targetRef);
		Predicate<Integer> isEqualPredicate = Predicate.isEqual(6);
		result(isEqualPredicate, 6);
		
		// The Predicate interface's static not() method reverses the result of the calculation performed
		// by its predicate argument. While the negate() method changes the result of an existing predicate,
		// the not() method changes the result of a predicate argument.
		// Ex:
		// static <T> Predicate<T> not(Predicate<T> target);	[Java 11]
		System.out.println(p.and(Predicate.not(x -> x%2 == 1)).test(8));
		
	}

	public static <T> void result(Predicate<T> p, T arg) {
		if (p.test(arg)) {
			System.out.println("Satisfy this condition.");
		} else {
			System.out.println("Do not satisfy this condition.");
		}
	}
	
}


