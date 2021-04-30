package com.manhpd.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.GroupedObservable;

public class GroupingOperator {

    public static void main(String[] args) {
        usingGroupBy();
    }

    public static void usingGroupBy() {
        Observable<String> source = Observable.just("alpha", "beta", "gamma");
        Observable<GroupedObservable<Integer, String>> byLengths = source.groupBy(s -> s.length());

//        byLengths.flatMapSingle(grp -> grp.toList())
//            .subscribe(System.out::println);

        byLengths.flatMap(grp -> Observable.fromArray(grp.toList()))
                .subscribe(s -> {
                    System.out.println("Received: " + s.toString());
                });
    }

}
