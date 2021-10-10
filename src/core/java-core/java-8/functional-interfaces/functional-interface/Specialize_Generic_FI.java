package com.manhpd.functional_interface;

public class Specialize_Generic_FI {

	public static void main(String[] args) {
		TwoIntsProcessor multiplyInts = new TwoIntsProcessor() {

			@Override
			public Integer process(Integer t1, Integer t2) {
				return t1 * t2;
			}
		};

		TwoIntsProcessor subtractInts = new TwoIntsProcessor() {

			@Override
			public Integer process(Integer t1, Integer t2) {
				return t1 - t2;
			}
		};

		TwoIntsProcessorAbstract divideInts = new TwoIntsProcessorAbstract() {

			@Override
			public Integer process(Integer t1, Integer t2) {
				return t1 / t2;
			}
		};

		System.out.println(multiplyInts.process(4, 9));
		System.out.println(subtractInts.process(5, 10));
		System.out.println(divideInts.process(124, 12));
	}

}

@FunctionalInterface
interface TwoArgsProcessors<T> {
	T process(T t1, T t2);
}

@FunctionalInterface
interface TwoIntsProcessor extends TwoArgsProcessors<Integer> {
	// nothing to do
}

abstract class TwoIntsProcessorAbstract implements TwoArgsProcessors<Integer> {
	// nothing to do
}