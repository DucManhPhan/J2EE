package com.manhpd.operators;

import io.reactivex.rxjava3.core.Observable;

public class UsingConditionalOperator {

    public static void main(String[] args) {
        usingTakeWhile();
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

}
