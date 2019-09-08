package com.manhpd.function;

import java.util.function.Function;

/**
 * Function is a functional interface with two type parameters T and R. Its functional
 * method, called apply() takes an argument of type T and returns an object of type R.
 * Funtions are ideal for converting an object of type T to one of type R.
 *
 * Ex:
 * @FunctionalInterface
 * public interface Function<T, R> {
 * 		R apply(T t);
 * 		...
 * }
 */
public class Introduction_Function {

	public static void main(String[] args) {
		Function<String, Integer> f = x -> {
			System.out.println();
			return Integer.parseInt(x);
		};

		f.apply("20");
	}
}
