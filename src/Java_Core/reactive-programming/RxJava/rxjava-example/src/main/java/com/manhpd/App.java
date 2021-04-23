package com.manhpd;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
//        useIntermediateOperator();
//        useFromIterable();
        defineCustomeObserver();
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

    public static void useFromIterable() {
        List<String> strs = Arrays.asList("alpha", "beta", "gamma");
        Observable<String> source = Observable.fromIterable(strs);

        source.subscribe(s -> System.out.println(s));
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
            try {
                emitter.onNext("alpha");
                emitter.onNext("beta");
                emitter.onNext("gamma");
                emitter.onComplete();
            } catch (Throwable e) {
                emitter.onError(e);
            }
        });

        source.subscribe(s -> System.out.println("RECEIVED: " + s),
                         Throwable::printStackTrace);
    }

    public static void useIntermediateOperator() {
        Observable<String> source = Observable.create(emitter -> {
            try {
                emitter.onNext("alpha");
                emitter.onNext("beta");
                emitter.onNext("gamma");
                emitter.onComplete();
            } catch (Throwable e) {
                emitter.onError(e);
            }
        });

        Observable<Integer> lengths = source.map(String::length);
        Observable<Integer> filtered = lengths.filter(i -> i >= 5);
        filtered.subscribe(s -> System.out.println("RECEIVED: " + s));
    }

    public static void useDisposable() {
        Disposable d = Observable.just("Hello world!")
                .delay(1, TimeUnit.SECONDS)
                .subscribeWith(new DisposableObserver<String>() {
                    @Override public void onStart() {
                        System.out.println("Start!");
                    }
                    @Override public void onNext(String t) {
                        System.out.println(t);
                    }
                    @Override public void onError(Throwable t) {
                        t.printStackTrace();
                    }
                    @Override public void onComplete() {
                        System.out.println("Done!");
                    }
                });

        sleep(500);
        // the sequence can now be disposed via dispose()
        d.dispose();
    }

    public static void defineCustomeObserver() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma");
        source.map(String::length)
                .filter(i -> i >= 5)
                .subscribe(i -> System.out.println(i),
                        Throwable::printStackTrace,
                        () -> System.out.println("Done!"));
    }

}

