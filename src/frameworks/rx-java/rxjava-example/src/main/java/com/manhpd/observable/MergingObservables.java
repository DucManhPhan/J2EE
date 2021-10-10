package com.manhpd.observable;

import com.manhpd.utils.ReactiveUtils;
import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MergingObservables {

    public static void main(String[] args) {
//        mergeObservables();
//        usingMergeArray();
//        usingFlatMap();
//        flatMapWithInfiniteObservable();
        flatMapWithCombiner();
    }

    public static void mergeObservables() {
        Observable<String> source1 = Observable.just("Alpha", "Beta");
        Observable<String> source2 = Observable.just("Zeta", "Eta");

        // 1st way: using merge() factory
//        Observable.merge(source1, source2)
//                .subscribe(i -> System.out.println("Received: " + i));

        // 2nd way: using merge() factory method that accepts a list of observables
        List<Observable<String>> sources = Arrays.asList(source1, source2);
        Observable.merge(sources)
                .subscribe(i -> System.out.println("Received: " + i));

        // 3rd way: using mergeWith() method
//        source1.mergeWith(source2)
//                .subscribe(i -> System.out.println("Received: " + i));
    }

    public static void usingMergeArray() {
        Observable<String> source1 = Observable.just("Alpha", "Beta");
        Observable<String> source2 = Observable.just("Zeta", "Eta");

        Observable.mergeArray(source1, source2)
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public static void usingFlatMap() {
        Observable.just("One", "Two", "Three")
                .flatMap(s -> Observable.fromArray(s.split("")))
                .subscribe(System.out::println);
    }

    public static void flatMapWithInfiniteObservable() {
        Observable.just(2, 3, 10, 7)
                .flatMap(i -> Observable.interval(i, TimeUnit.SECONDS)
                        .map(i2 -> i + "s interval: " +
                                ((i + 1) * i) + " seconds elapsed"))
                .subscribe(System.out::println);

        ReactiveUtils.sleep(12000);
    }

    public static void flatMapWithCombiner() {
        Observable.just("One", "Two", "Three")
                .flatMap(s -> Observable.fromArray(s.split("")), (s, r) -> s + "-" + r)
                .subscribe(System.out::println);
    }

}
