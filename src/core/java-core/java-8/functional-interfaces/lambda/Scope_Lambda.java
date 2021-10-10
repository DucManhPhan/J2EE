package com.manhpd.lambda;

/**
 * The scope of a lambda expression coincides with the definition of the
 * expression. Therefore, any variables that are in scope at the definition of a
 * lambda expression are in scope of the functional method it represents. This
 * includes fields and final or effectively final local variables.
 *
 */
public class Scope_Lambda {

	private static int field = 5;

	public static void main(String[] args) {
		int local = 12;

		// Local variables used in lambda expression must be final or effectively final.
		FIVoid lambdavoid = x -> System.out.println(x + field + local);
		lambdavoid.print(6);
	}

}

@FunctionalInterface
interface FIVoid {
	void print(int x);
}