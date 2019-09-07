package com.manhpd.lambda;

public class Lambda_Bodies_Block_Form {

	public static void main(String[] args) {

		// expression form
		printCommand cmd_print = x -> System.out.println(x);

		// In block form, the lambda body may consist of multiple statements, each
		// ending in a semicolon.
		// If a lambda expression in block form returns a value, a return statement must
		// be provided.
		// The local variables in lambda expression do not have the similiar name with
		// the other variable outside.
		// Because scope of lambda expression coincides with the definition of
		// expression.
		// A lambda expression may also contain exception handling.
		printCommand cmd_print_block = x -> {
			System.out.println(x);
		};

		cmd_print.print("Hello, world!\n");
		cmd_print_block.print("Thanks for everyone.");

		// Use exception handling
		int[] array = { 1, 2, 4, 6, 8 };
		valueOfElement valueOf = i -> {
			try {
				int value = array[i];
				System.out.println(value);
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Index " + i + " is out of bounds.");
			}
		};
		
		valueOf.print(-1);
	}
}

@FunctionalInterface
interface printCommand {
	void print(String s);
}

@FunctionalInterface
interface valueOfElement {
	void print(int index);
}
