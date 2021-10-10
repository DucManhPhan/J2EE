package com.manhpd;


import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Reactor is integrated into Spring 5, Spring Boot 2.0
 * Ex: Spring Webflux support Reactive Streams
 */
public class App
{
    public static void main( String[] args ) {
        Integer[] numbers = new Integer[]{1, 3, 4, 6, 10};
        List<Integer> lst = new ArrayList<Integer>(Arrays.asList(numbers));

        // Check whether Flux will run in main thread
        Flux.fromIterable(lst)
            .flatMap(i -> Flux.just(2 * i))
            .subscribe(value -> System.out.println(value + " consumer processed "
                    + " using thread: " + Thread.currentThread().getName()));

        // Running on other threads
        Flux.fromIterable(lst)
            .log()
            .flatMap(i -> Flux.just(i * 2))
            .subscribeOn(Schedulers.elastic())
            .subscribe(value -> System.out.println(Thread.currentThread().getName() + " : " + value));
    }
}
