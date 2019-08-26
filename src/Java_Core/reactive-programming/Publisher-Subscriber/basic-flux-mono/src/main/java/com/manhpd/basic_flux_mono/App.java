package com.manhpd.basic_flux_mono;

import java.util.LinkedList;
import java.util.List;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		testFibonacci();
	}
	
	/**
	 * Create fibonacci sequences with generate() method of Flux
	 * 
	 */
	public static void testFibonacci() {
		Flux<Long> generator = Flux.generate(
				() -> Tuples.<Long, Long> of(0L, 1L),	// initialize part
				(state, sink) -> {
					sink.next(state.getT1());
					return Tuples.of(state.getT2(), state.getT1() + state.getT2());
				});
		List<Long> series = new LinkedList<Long>();
		generator.take(50).subscribe(t -> series.add(t));
		System.out.println(series);
	}
}
