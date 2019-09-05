package com.manhpd;

/**
 * In Java 8, interfaces were allowed to have static and default methods. A
 * static method has a single instance associated with the interface. A static
 * method can be called without creation of an object. A default method is an
 * implementation provided by the interface that does not have to be overriden
 * by an implementing class. Default methods help in the compilation of legacy
 * code.
 *
 */
public class Interfaces_Java_8 {

	public static void main(String[] args) {
		I2.method1();
		I2 objC2 = new C2();
		I2 objC3 = new C3();

		System.out.println(objC2.method2("Hello"));
		System.out.println(objC3.method2("World"));
	}

}

interface I2 {
	String s = "I2";

	static void method1() {
		System.out.println(s);
	}

	default String method2(String x) {
		return s + x;
	}
}

class C2 implements I2 {

	@Override
	public String method2(String x) {
		return x;
	}
}

class C3 implements I2 {
	// nothing to do
}
