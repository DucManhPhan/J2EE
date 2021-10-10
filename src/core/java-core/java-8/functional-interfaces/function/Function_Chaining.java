package com.manhpd.function;

import java.util.function.Function;

/**
 * The Function interface has default methods that return new functions,
 * support chains of functions that can perform many conversions in series.
 * 
 * andThen() method
 * Ex:
 * default <V> Function<T, V> andThen(Function<? super R, ? extends V> after);
 * 
 * 
 */
public class Function_Chaining {

	public static void main(String[] args) {
		// The Function interface's default andThen() method applies an additional operation
		// after the operation specified by the apply() method completes. It can be used to
		// create chains of functions.
		Function<String, Boolean> fsb = x -> Boolean.parseBoolean(x);
		System.out.println(fsb.andThen(x -> x == true ? 1 : 0)
							  .apply("true"));
	}
}
