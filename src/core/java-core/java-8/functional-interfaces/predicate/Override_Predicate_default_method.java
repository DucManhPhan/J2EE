package com.manhpd.predicate;

import java.util.function.Predicate;


/**
 * So, if we need to check null or some other conditions in variable of Predicate,
 * we should override some default methods for it.
 *
 */
public class Override_Predicate_default_method {

	// Program that check if a string starts with the character "a" and is greater than four characters in length.
	public static void main(String[] args) {

		// 1st way: do not check null for input string
		Predicate<String> isLengthGreaterThan = x -> x.length() > 4;
		Predicate<String> startWith = x -> x.charAt(0) == 'a';
		System.out.println(isLengthGreaterThan.and(startWith)
											  .test("alpha"));

		// 2nd way: Override default methods in Predicate to check null for input string
		Predicate<String> checkNullForLengthString = new Predicate<String>() {
			@Override
			public boolean test(String s) {
				return s.length() > 4;
			}

			@Override
			public Predicate<String> and(Predicate<? super String> p) {
				return x -> x == null ? false : test(x) && p.test(x);
			}
		};

		System.out.println(checkNullForLengthString.and(startWith).test("alpha"));
	}
}
