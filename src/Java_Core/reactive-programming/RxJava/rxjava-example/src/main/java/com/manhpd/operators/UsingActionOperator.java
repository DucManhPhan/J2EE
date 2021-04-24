package com.manhpd.operators;

import io.reactivex.rxjava3.core.Observable;

public class UsingActionOperator {

    public static void main(String[] args) {
//        usingDoOnNext();
//        usingDoAfterNext();
//        usingDoOnComplete();
        usingDoOnError();
    }

    public static void usingDoOnNext() {
        Observable.just("alpha", "beta", "gamma")
                .doOnNext(s -> System.out.println("Processing: " + s))
                .map(String::length)
                .doOnNext(i -> System.out.println("Length: " + i))
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public static void usingDoAfterNext() {
        Observable.just("alpha", "beta", "gamma")
                .doAfterNext(s -> System.out.println("After: " + s))
                .map(String::length)
//                .doAfterNext(s -> System.out.println("After: " + s))
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public static void usingDoOnComplete() {
        Observable.just("alpha", "beta", "gamma")
                .doOnComplete(() -> System.out.println("Source is done emitting"))
                .map(String::length)
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public static void usingDoOnError() {
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .doOnError(e -> System.out.println("Source failed!"))
                .map(i -> 10 / i)
                .doOnError(e -> System.out.println("Division failed!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
    }

}
