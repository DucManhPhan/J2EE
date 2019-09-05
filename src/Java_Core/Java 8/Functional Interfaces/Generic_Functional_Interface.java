package com.manhpd;

public class Generic_Functional_Interface {

	public static void main(String[] args) {

		TwoArgsProcessor<Integer> fi = new TwoArgsProcessor<>() {

			@Override
			public Integer process(Integer t1, Integer t2) {
				return t1 + t2;
			}
		};

		System.out.println("Sum of 3 and 5 is: " + fi.process(3, 5));
	}

}

/**
 * Many functional interfaces are generic for one or more types. Below is a
 * functional interface that is generic for type T and has a functional method
 * that accepts two arguments of type T and return a value of type T.
 *
 * @param <T>
 */
@FunctionalInterface
interface TwoArgsProcessor<T> {
	T process(T t1, T t2);
}