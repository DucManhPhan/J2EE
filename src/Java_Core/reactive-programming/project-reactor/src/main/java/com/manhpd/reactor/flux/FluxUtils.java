package com.manhpd.reactor.flux;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;


/**
 * Flux<T> is the main entry point for Reactor reactive stream.
 * It is similar to RxJava's Observable.
 *
 * Schedulers.parallel() method provides a thread cache for executing in parallel.
 */
public class FluxUtils {

    public static void main(String[] args) {
        Flux.range(1, 100)
            .publishOn(Schedulers.parallel())       // Reactor uses Schedulers to decide on what thread to run
            .subscribe(v -> System.out.println(v));
    }
}

// - onErrorResume(Function): Takes the exception and
//returns a different Publisher as a fallback or secondary
//stream.
//
// - onErrorMap(Function): Takes the exception and allows
//you to modify it or return a completely new Exception if
//you prefer.
//
// - onErrorReturn(T): Provides a default value to use
//when an error arises.
//
// - doOnError(Consumer<? super Throwable>): Allows
//you to handle the error without effecting the underlying
//stream in any way.