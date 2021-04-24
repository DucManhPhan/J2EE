package com.manhpd.operators;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.List;

public class UsingConditionalOperator {

    public static void main(String[] args) {
//        usingTakeWhile();
//        usingSkipWhile();
//        usingDefaultIfEmpty();
        usingSwitchIfEmpty();
    }

    /**
     * When the value of a condition from takeWhile() method is false at the first time, onComplete event
     * will be pushed to the Observer, and disposed the used resources.
     *
     * Therefore, if we change the condition i < 5 to i > 5, no emissions will be fired.
     */
    public static void usingTakeWhile() {
        Observable.range(1, 100)
                .takeWhile(i -> i > 5)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }

    /**
     * When the value of a condition from skipWhile() operator is false at the first time, all subsequent emissions are
     * not skipped anymore and flow downstream.
     */
    public static void usingSkipWhile() {
        List<Integer> ints = Arrays.asList(5, 6, 3, 4, 0, 1);
        Observable.range(1, 100)
                .skipWhile(i -> i > 5)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }

    public static void usingDefaultIfEmpty() {
        Observable<String> items = Observable.just("alpha", "beta");
        items.filter(s -> s.startsWith("Z"))
//        Observable.empty()
//                .defaultIfEmpty("None")
                .subscribe(System.out::println);
    }

    public static void usingSwitchIfEmpty() {
        Observable.just("Alpha", "Beta", "Gamma")
                .filter(s -> s.startsWith("Z"))
                .switchIfEmpty(Observable.just("Zeta", "Eta", "Theta"))
                .subscribe(i -> System.out.println("Received: " + i),
                        e -> System.out.println("Error: " + e));
    }

}
