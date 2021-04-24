package com.manhpd;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.observers.DisposableObserver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class UsingObservable {

    private static int start = 1;

    private static int count = 3;

    public static void main(String[] args) {
//        useIntermediateOperator();
//        useFromIterable();
//        defineCustomeObserver();
//        usingColdObservable();
//        usingConnectableObservable();
//        usingObservableRange();
//        usingObservableInterval();
//        usingObservableEmpty();
//        usingObservableNever();
//        notUsingObservableDefer();
//        usingObservableDefer();
//        notUsingObservableFromCallable();
        usingObservableFromCallable();
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

    public static void usingColdObservable() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma");
        source.subscribe(s -> System.out.println("Observer 1: " + s));
        source.subscribe(s -> System.out.println("Observer 2: " + s));
    }

    /**
     * using ConnectableObservable when we want to emit each emission to all observers at the same time
     */
    public static void usingConnectableObservable() {
        ConnectableObservable<String> source = Observable.just("alpha", "beta", "gamma").publish();

        source.subscribe(s -> System.out.println("Observer 1: " + s));
        source.map(String::length)
              .subscribe(s -> System.out.println("Observer 2: " + s));

        source.connect();

        source.subscribe(s -> System.out.println("Observer 3: " + s));
    }

    public static void usingObservableRange() {
        Observable.range(1, 3)
                  .subscribe(s -> System.out.println("RECEIVED: " + s));
    }

    /**
     * Because each of these two observers actually gets its own emissions, each starting at 0,
     * so the Observable.interval() is cold observable
     */
    public static void usingObservableInterval() {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);

        seconds.subscribe(s -> System.out.println("Observer 1: " + s));
        sleep(3000);

        seconds.subscribe(s -> System.out.println("Observer 2: " + s));
        sleep(3000);
    }

    public static void usingObservableFuture() {
        Future<String> future = null;
        Observable.fromFuture(future)
                .map(String::length)
                .subscribe(System.out::println);
    }

    public static void usingObservableEmpty() {
        Observable<String> empty = Observable.empty();
        empty.subscribe(System.out::println,
                        Throwable::printStackTrace,
                        () -> System.out.println("Done!"));
    }

    public static void usingObservableNever() {
        Observable<String> empty = Observable.never();
        empty.subscribe(System.out::println,
                Throwable::printStackTrace,
                () -> System.out.println("Done!"));
        sleep(3000);
    }

    public static void usingObservableError() {
        Observable.error(new Exception("Crash and burn!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                           e -> System.out.println("Error captured: " + e),
                           () -> System.out.println("Done!"));
    }

    public static void notUsingObservableDefer() {
        Observable<Integer> source = Observable.range(start, count);
        source.subscribe(i -> System.out.println("Observer 1: " + i));

        //modify count
        count = 5;
        source.subscribe(i -> System.out.println("Observer 2: " + i));
    }

    public static void usingObservableDefer() {
        Observable<Integer> source = Observable.defer(() -> Observable.range(start, count));
        source.subscribe(i -> System.out.println("Observer 1: " + i));

        //modify count
        count = 5;
        source.subscribe(i -> System.out.println("Observer 2: " + i));
    }

    public static void notUsingObservableFromCallable() {
        Observable.just(1 / 0)
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("Error captured: " + e));
    }

    public static void usingObservableFromCallable() {
        Observable.fromCallable(() -> 1 / 0)
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("Error captured: " + e));
    }


}

