package com.manhpd.filtering_data;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.util.function.Predicate;

public class FilteringData {

    public static void main(String[] args) {
//        testFilterData();

//        testFilterWhenData();

//        testTakeOperator();

        testTakeLastOperator();
    }

    private static Flux<Long> createFibonacci() {
        Flux<Long> fibonacciGenerator = Flux.generate(() -> Tuples.<Long, Long>of(0L, 1L),
                                                    (state, sink) -> {
                                                        if (state.getT1() < 0) {
                                                            sink.complete();
                                                        } else {
                                                            sink.next(state.getT1());
                                                        }

                                                        return Tuples.of(state.getT2(), state.getT1() + state.getT2());
                                                    });
        return fibonacciGenerator;
    }

    private static void testFilterData() {
        Flux<Long> fibonacciFlux = createFibonacci();
        Predicate<Long> evenPredicate = t -> t%2 == 0;
        fibonacciFlux.filter(evenPredicate).subscribe(t -> System.out.println(t));
    }

    private static void testFilterWhenData() {
        createFibonacci().filterWhen(a -> Mono.just(a < 10))
                         .subscribe(t -> System.out.println(t));
    }

    private static void testTakeOperator() {
        createFibonacci().take(5).subscribe(t -> System.out.println(t));
    }

    /**
     * Used to get the last number of elements in stream.
     */
    private static void testTakeLastOperator() {
        createFibonacci().takeLast(10).subscribe(t -> System.out.println(t));
    }

    private static void testFibonacci() {
        createFibonacci().subscribe(t -> {
            System.out.println(t);
        });
    }

}
