package com.manhpd.operators;

import io.reactivex.rxjava3.core.Observable;

public class UsingActionOperator {

    public static void main(String[] args) {
//        usingDoOnNext();
//        usingDoAfterNext();
        usingDoOnComplete();
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

}
