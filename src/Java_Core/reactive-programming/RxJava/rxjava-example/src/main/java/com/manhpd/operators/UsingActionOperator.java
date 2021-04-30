package com.manhpd.operators;

import com.manhpd.utils.ReactiveUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

public class UsingActionOperator {

    public static void main(String[] args) {
//        usingDoOnNext();
//        usingDoAfterNext();
//        usingDoOnComplete();
//        usingDoOnError();
//        usingDoOnEach();
//        usingDoOnSubscribe();
//        usingDoOnDispose();
        usingDoFinally();
    }

    public static void usingDoOnNext() {
        Observable.just("alpha", "beta", "gamma")
                .doOnNext(s -> System.out.println("Processing: " + s))
                .map(String::length)
                .doOnNext(i -> System.out.println("Length: " + i))
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public static void usingDoAfterNext() {
        Observable.just("alpha", "beta", "gamma")
                .doAfterNext(s -> System.out.println("After: " + s))
                .map(String::length)
//                .doAfterNext(s -> System.out.println("After: " + s))
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public static void usingDoOnComplete() {
        Observable.just("alpha", "beta", "gamma")
                .doOnComplete(() -> System.out.println("Source is done emitting"))
                .map(String::length)
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public static void usingDoOnError() {
        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .doOnError(e -> System.out.println("Source failed!"))
                .map(i -> 10 / i)
                .doOnError(e -> System.out.println("Division failed!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));
    }

    public static void usingDoOnEach() {
        Observable.just("alpha", "beta", "gamma")
                .doOnEach(s -> System.out.println("doOnEach: " + s.getError() + ", " + s.getValue()))
                .subscribe(i -> System.out.println("Received: " + i));
    }

    /**
     * If we want to know when the subscription is created, use doOnSubscribe() operator. In the lambda expression that provided
     * as the parameter of doOnSubscribe() operator, this lambda expression accepts an Disposable object, then we can terminate
     * this action immediately
     */
    public static void usingDoOnSubscribe() {
        Observable.just("alpha", "beta", "gamma")
                .doOnSubscribe(d -> {
                    System.out.println("Subscribing!");
                    d.dispose();
                    System.out.println("Then, terminated");
                })
                .doOnDispose(() -> System.out.println("Disposing!"))
                .subscribe(i -> System.out.println("Received: " + i));
    }

    /**
     * If we want to dispose an Subscription, a stream in Observable-Observer, we can do something like
     * the below code
     */
    public static void usingDoOnDispose() {
        Disposable disp = Observable.interval(1, TimeUnit.SECONDS)
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
//                .doOnDispose(() -> System.out.println("Disposing!"))
                .doFinally(() -> System.out.println("The end of an action"))
                .subscribe(i -> System.out.println("Received: " + i));

        ReactiveUtils.sleep(3000);
        disp.dispose();
        ReactiveUtils.sleep(3000);
    }

    public static void usingDoOnSuccess() {
        Observable.just(5, 3, 7)
                .reduce((total, next) -> total + next)
                .doOnSuccess(i -> System.out.println("Emitting: " + i))
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public static void usingDoFinally() {
        Observable.just("alpha", "beta", "gamma")
                .doFinally(() -> System.out.println("doFinally!"))
                .doAfterTerminate(() -> System.out.println("doAfterTerminate!"))
                .subscribe(i -> System.out.println("Received: " + i));
    }

}
