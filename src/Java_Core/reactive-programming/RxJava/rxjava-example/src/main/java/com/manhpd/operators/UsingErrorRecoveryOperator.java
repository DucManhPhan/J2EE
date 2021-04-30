package com.manhpd.operators;

import io.reactivex.rxjava3.core.Observable;

public class UsingErrorRecoveryOperator {

    public static void main(String[] args) {
//        throwException();
//        usingOnErrorReturnItem();
        usingOnErrorReturn();
    }

    public static void throwException() {
        Observable.just(5, 4, 2, 0, 3)
                .map(i -> 10 / i)
                .subscribe(i -> System.out.println("Received: " + i),
                            e -> System.out.println("Error: " + e));
    }

    public static void usingOnErrorReturnItem() {
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10 / i)
//                .doOnError(e -> System.out.println("Exception!"))
                .doFinally(() -> System.out.println("The end"))
                .onErrorReturnItem(-1)
                .subscribe(i -> System.out.println("Received: " + i),
                            e -> System.out.println("Error: " + e));
    }

    public static void usingOnErrorReturn() {
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10 / i)
                .onErrorReturn(e -> e instanceof ArithmeticException ? -1 : 0)
                .subscribe(i -> System.out.println("Received: " + i),
                        e -> System.out.println("Error: " + e));
    }
}
