package com.manhpd;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        streamEvents();
    }

    public static void streamData() {
        Observable<String> strs = Observable.just("alpha", "beta", "gamma");
        strs.map(s -> s.length())
                .subscribe(s -> System.out.println(s));
    }

    public static void streamEvents() {
        Observable<Long> secondIntervals = Observable.interval(1, TimeUnit.SECONDS);
        secondIntervals.subscribe(s -> System.out.println(s));

        sleep(5000);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void useCreateObservable() {
        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("alpha");
            emitter.onNext("beta");
            emitter.onNext("gamma");
            emitter.onComplete();
        });

        source.subscribe(s -> System.out.println("RECEIVED: " + s));
    }

}
