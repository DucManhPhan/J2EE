package com.manhpd.predicate;

import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

/**
 * With each primitive data types - Integers, Longs and Doubles, we have some
 * corresponde such as IntPredicate, LongPredicate, DoublePredicate
 * 
 * Ex:
 * @FunctionalInterface
 * public interface IntPredicate {
 * 		boolean test(int value);
 * 
 * 		// other default methods
 * 		// ...
 * }
 * 
 * The isEqual() method is not defined for predicate specializations since 
 * a lambda expression is easily created using the == operator and an autoboxed number.
 * 
 */
public class Specialization_Predicates {

	public static void main(String[] args) {
		IntPredicate i = x -> x > 5;
		LongPredicate l = y -> y%2 ==0;
		DoublePredicate d = z -> z > 8.0;

		System.out.println(i.test(2));
		System.out.println(l.or(a -> a == 6L).test(10L));
		System.out.println(d.and(b -> b < 9.0).test(8.5));
	}
}
