package com.manhpd;

import java.util.Random;

/**
 * In Java 9, interfaces were allowed to have private methods. Private methods
 * are useful to call from default methods.
 * Java 9 also has private static methods. Since the static methods of an interface
 * only be called by public static methods defined in the interface.
 * 
 *
 */
public class Interfaces_Java_9 {

	public static void main(String[] args) {
		I3 objC4 = new C4();
		System.out.println(objC4.M1("Hello"));
		
		System.out.println(I4.getName("Barrack", "male"));
		System.out.println(I4.getName("Clinton", "male"));
	}

}

interface I3 {
	private int getNumber() {
		return (new Random()).nextInt(100);
	}

	default String M1(String s) {
		return s + this.getNumber();
	}
}

interface I4 {
	private static String getPrefix(String p) {
		return p.contentEquals("male") ? "Mr. " : "Ms. ";
	}
	
	public static String getName(String n, String p) {
		return getPrefix(p) + n;
	}
}

class C4 implements I3 {
	// nothing to do
}