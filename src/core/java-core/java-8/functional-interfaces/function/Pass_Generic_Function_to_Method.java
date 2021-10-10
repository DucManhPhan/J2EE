package com.manhpd.function;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * To pass a generic function to a method, two type parameters must be specified in the method's header.
 * 
 * @author Manh Hang
 *
 */
public class Pass_Generic_Function_to_Method {

	public static void main(String[] args) {
		Function<String, Integer> fsi = x -> Integer.parseInt(x);
		Function<Integer, String> fis = x -> Integer.toString(x);

		Integer i = transform("100", fsi);
		String s = transform(200, fis);

		System.out.println(i);
		System.out.println(s);
		
		List<Function<String, ? extends Number>> list = new ArrayList<>();
		list.add(x -> Byte.parseByte(x));
		list.add(x -> Short.parseShort(x));
		list.add(x -> Integer.parseInt(x));
		list.add(x -> Long.parseLong(x));
		list.add(x -> Float.parseFloat(x));
		list.add(x -> Double.parseDouble(x));
		
		String[] numbers = { "10", "20", "30", "40", "50", "60" };
		Number[] results = new Number[numbers.length];
		
		for (int i1 = 0; i1 < numbers.length; ++i1) {
			results[i1] = parse(numbers[i1], list.get(i1));
			System.out.println(results[i1]);
		}
	}

	private static <T, R> R transform(T t, Function<T, R> f) {
		return f.apply(t);
	}
	
	private static <R extends Number> R parse(String x, Function<String, R> f) {
		return f.apply(x);
	}
}

