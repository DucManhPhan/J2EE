package com.manhpd.hot_cold_publisher;

import java.util.concurrent.CountDownLatch;

import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;
import reactor.util.function.Tuples;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws InterruptedException {
		// testColdPublisher();

		testHotPublisher();
	}

	public static void testColdPublisher() {
		Flux<Long> fibonacciGenerator = Flux.generate(
				() -> Tuples.<Long, Long>of(0L, 1L),
				(state, sink) -> {
					sink.next(state.getT1());
					return Tuples.of(state.getT2(), state.getT1() + state.getT2());
				});

		fibonacciGenerator.take(5).subscribe(t -> System.out.println("1. " + t));
		fibonacciGenerator.take(5).subscribe(t -> System.out.println("2. " + t));
	}
	
	public static void testHotPublisher() throws InterruptedException {
		final UnicastProcessor<Long> hotSource = UnicastProcessor.create();
		final Flux<Long> hotFlux = hotSource.publish().autoConnect();
		hotFlux.take(5).subscribe(t -> System.out.println("1. " + t));
		CountDownLatch latch = new CountDownLatch(2);
		new Thread(() -> {
			int c1 = 0;
			int c2 = 1;

			while (c1 < 1000) {
				hotSource.onNext(Long.valueOf(c1));
				int sum = c1 + c2;
				c1 = c2;
				c2 = sum;
				
				if (c1 == 144) {
					hotFlux.subscribe(t -> System.out.println("2. " + t));
				}
			}

			hotSource.onComplete();
			latch.countDown();
		}).start();

		latch.await();
	}
}
