package com.manhpd.operators;

import io.reactivex.rxjava3.core.Observable;

public class UsingErrorRecoveryOperator {

    public static void main(String[] args) {
        throwException();
    }

    public static void throwException() {
        Observable.just(5, 4, 2, 0, 3)
                .map(i -> 10 / i)
                .subscribe(i -> System.out.println("Received: " + i),
                            e -> System.out.println("Error: " + e));
    }

}
