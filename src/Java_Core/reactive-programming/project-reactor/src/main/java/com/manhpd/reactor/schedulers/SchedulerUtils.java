package com.manhpd.reactor.schedulers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class SchedulerUtils {

    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4, 5};
        List<Integer> squares = Arrays.asList(arr);
        Flux.range(1, 64).flatMap(v -> Mono.just(v)
                                                        .subscribeOn(Schedulers.newSingle("comp"))
                                                        .map(w -> w * w))
                                    .doOnError(ex -> ex.printStackTrace())
                                    .doOnComplete(() -> System.out.println("Completed"))
                                    .subscribeOn(Schedulers.immediate())
                                    .subscribe(squares::add);
    }

    /**
     * Flux#delayElements(Duration) uses the Schedulers.parallel() instance.
     *
     * The Schedulers.parallel() creates as many threads as there are CPUs but at least 4.
     */
    public static void testDelayElements() {
        Flux<Integer> flux2 = Flux.range(0, 2).delayElements(Duration.ofMillis(1));
        SchedulerUtils.createSubscribers(flux2);
}

    public static void createSubscribers(Flux<Integer> flux) {
        IntStream.range(1, 5).forEach(value ->
                flux.subscribe(integer -> System.out.println(value + " consumer processed "
                        + integer + " using thread: " + Thread.currentThread().getName())));
    }

    /**
     * With the map operator we create another Flux (flux2) and when we subscribe to this flux2 instance,
     * then flux2 implicitly subscribes to flux1 as well.
     * And when the flux1 starts to emit elements it will call flux2 which will call our Subscriber.
     * We can notice that there is a subscription process and emitting process.
     * In Reactor terminology the flux1 would be the upstream and flux2 the downstream.
     *
     */
    public static void useMultipleFlux() {
        Flux<String> flux1 = Flux.just("foo", "bar");
        Flux<String> flux2 = flux1.map(s -> s.toUpperCase());
        flux2.subscribe(s -> System.out.println(s));
    }

}

/**
 * Some static method of Schedulers class:
 * - Schedulers.immediate() - The current thread.
 *
 * - Schedulers.single() - A single, reusable thread. Note that this method reuses the same thread for all callers, until
 *                         the Scheduler is disposed. If we want a per-call delicated thread,
 *                         use Schedulers.newSingle() for each call.
 *
 * - Schedulers.newSingle() - Creates a new Thread each time it is called to be used by the underlying Flux.
 *
 * - Schedulers.elastic() - An elastic thread pool. It creates new worker pool as needed and reuses idle ones.
 *                          Worker pools that stay idle for too long (default is 60 seconds).
 *                          This is a good choice for I/O blocking work for instance.
 *                          Schedulers.elastic() is a handy way to give a blocking process its own thread, so that
 *                          it does not tie up other resources.
 *
 * - Schedulers.parallel() - A fixed pool of workers. It creates as many workers as you have CPU cores.
 *
 * - Schedulers.fromExecutor(Executor) - Creates a Scheduler to use the given Executor, allowing you to use your extensive
 *                                       knowledge of Java's Executors.
 *
 */