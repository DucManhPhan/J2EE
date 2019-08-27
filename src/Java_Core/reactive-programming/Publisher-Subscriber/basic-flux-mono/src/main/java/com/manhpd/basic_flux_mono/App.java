package com.manhpd.basic_flux_mono;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.reactivestreams.Publisher;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;


/**
 * The sequence of steps:
 * 1. onSubscribe() - This is called when we subscribe to your.
 * 2. request(unbounded) - When we call subscribe, behind the scense, we are creating a Subscription.
 * 		This subscription requests elements from the stream.
 * 		In this case, it defaults to unbounded, meaning it requests every single element available.
 * 3. onNext() - This is called on every single element
 * 4. onComplete() - This is called last, after receiving the last element. There's actually a onError() as well,
 * 		which would be called if there is an exeception.
 * 
 * @author MP
 *
 */
public class App {

	private static String s = "";

	public static void main(String[] args) {
		// testFibonacci();
		
		// testFlux();
		
		testMappingDataOnStream();
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

	public static void testFlux() {
		Flux<String> just = Flux.just("1", "2", "3");
		System.out.println(just);

		List<Integer> elements = new ArrayList<>();
		Flux.just(1, 2, 3, 4)
		.log()
		.subscribe(elements::add);
	}

	public static void testMono() {
		Publisher<String> just = Mono.just("foo");
		System.out.println(just);
	}

	public static void testMappingDataOnStream() {
		List<Integer> elements = new ArrayList<>();
		Flux.just(1, 2, 3, 4)
		.log()
		.map(i -> i * 2)
		.doOnNext(App::print)
		.subscribe(elements::add);
	}

	private static Object print(Object o) {
		s = !s.isEmpty() ? s.concat("->") : s;
		s = s.concat(o.toString());
		System.out.println(s);
		return o;
	}
}
