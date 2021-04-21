package com.manhpd;

import rx.Observable;

public class Application {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        String[] letters = {"a", "b", "c", "d", "e", "f", "g"};
        Observable<String> observable = Observable.from(letters);
        observable.subscribe(System.out::println,
                             Throwable::printStackTrace,
                             () -> System.out.println("Completed"));
    }

}
