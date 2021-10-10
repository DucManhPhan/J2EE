package com.manhpd.multicasting;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.ThreadLocalRandom;

public class UsingMulticasting {

    public static void main(String[] args) {
//        usingMulticasting();
        howToUseMulticasting();
    }

    public static void usingMulticasting() {
        ConnectableObservable<Integer> ints = Observable.range(1, 3).publish();
        ints.subscribe(i -> System.out.println("Observer 1: " + i));
        ints.subscribe(i -> System.out.println("Observer 2: " + i));

        ints.connect();
    }

    public static void howToUseMulticasting() {
        ConnectableObservable<Integer> ints = Observable.range(1, 3)
                .map(i -> randomInt()).publish();
        ints.subscribe(i -> System.out.println("Observer 1: " + i));
        ints.subscribe(i -> System.out.println("Observer 2: " + i));

        ints.connect();
    }

    private static int randomInt() {
        return ThreadLocalRandom.current().nextInt(100000);
    }

}
