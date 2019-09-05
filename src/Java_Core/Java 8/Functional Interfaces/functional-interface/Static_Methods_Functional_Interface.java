package com.manhpd;

public class Static_Methods_Functional_Interface {

	public static void main(String[] args) {

		StringProcessor upperCaseSP = new StringProcessor() {

			@Override
			public String process(String s) {
				return s.toUpperCase();
			}
		};

		StringProcessor lowerCaseSP = new StringProcessor() {

			@Override
			public String process(String s) {
				return s.toLowerCase();
			}
		};

		String tmp = upperCaseSP.process("Hello");
		System.out.println(tmp);
		System.out.println("Is string lower case: " + StringProcessor.isLowerCase(tmp));
		System.out.println("Is string upper case: " + StringProcessor.isUpperCase(tmp));
	}

	/**
	 * A functional interface can have static methods. Static methods are useful to
	 * define helper methods that are not meant to be overriden by implementing
	 * classes.
	 *
	 */
	@FunctionalInterface
	interface StringProcessor {

		String process(String s);

		static boolean isLowerCase(String s) {
			boolean result = true;

			for (int i = 0; i < s.length() && result; ++i) {
				result &= Character.isLowerCase(s.charAt(i));
			}

			return result;
		}

		static boolean isUpperCase(String s) {
			boolean result = true;

			for (int i = 0; i < s.length() && result; ++i) {
				result &= Character.isUpperCase(s.charAt(i));
			}

			return result;
		}
	}

}
