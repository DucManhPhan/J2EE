package com.manhpd.operators;

import io.reactivex.rxjava3.core.Observable;

public class UsingReducingOperator {

    public static void main(String[] args) {
//        usingCount();
//        usingReduce();
//        usingAllOperator();
//        usingAnyOperator();
//        usingIsEmpty();
//        usingContainsOperator();
        usingSequenceEqual();
    }

    public static void usingCount() {
        Observable.just("alpha", "beta", "gamma")
                .count()
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public static void usingReduce() {
        // 1st sample
//        Observable.just(5, 3, 7)
//                .reduce((total, i) -> total + i)
//                .subscribe(s -> System.out.println("Received: " + s));

        // 2nd sample
        Observable.just(5, 3, 7)
                .reduce("", (total, i) -> total + (total.equals("") ? "" : ", ") + i)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public static void usingAllOperator() {
        // 1st sample
//        Observable.just(5, 3, 7, 11, 2, 14)
//                .all(i -> i < 10)
//                .subscribe(s -> System.out.println("Received: " + s));

        // 2nd sample: use for empty Observable
        Observable.just(5, 4, 7, 0, 3)
                .filter(i -> i > 10)
                .all(i -> i > 0)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public static void usingAnyOperator() {
        Observable.just(5, 4, 7, 0, 3)
//                .filter(i -> i > 10)
                .any(i -> i > 0)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public static void usingIsEmpty() {
        Observable.just(3, 6, 9, 2)
                .filter(i -> i < 10)
                .isEmpty()
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public static void usingContainsOperator() {
        Observable.just(5, 6, 9, 3)
                .contains(2)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public static void usingSequenceEqual() {
        Observable<Integer> obs1 = Observable.just(1, 2, 3);
        Observable<Integer> obs2 = Observable.just(1, 2, 3);
        Observable<Integer> obs3 = Observable.just(4, 4, 9);
        Observable<Integer> obs4 = Observable.just(1, 2);

        Observable.sequenceEqual(obs1, obs2)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.sequenceEqual(obs1, obs3)
                .subscribe(s -> System.out.println("Receiveed: " + s));

        Observable.sequenceEqual(obs1, obs4)
                .subscribe(s -> System.out.println("Receiveed: " + s));
    }

}
