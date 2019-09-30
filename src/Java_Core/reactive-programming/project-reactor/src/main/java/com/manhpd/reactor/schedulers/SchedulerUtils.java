package com.manhpd.reactor.schedulers;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;

public class SchedulerUtils {

    public static void main(String[] args) {
        List<Integer> squares = new ArrayList<>();
        Flux.range(1, 64).flatMap(v -> Mono.just(v)
                                                        .subscribeOn(Schedulers.newSingle("comp"))
                                                        .map(w -> w * w))
                                    .doOnError(ex -> ex.printStackTrace())
                                    .doOnComplete(() -> System.out.println("Completed"))
                                    .subscribeOn(Schedulers.immediate())
                                    .subscribe(squares::add);
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