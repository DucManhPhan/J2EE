package com.manhpd.operators;

import io.reactivex.rxjava3.core.Observable;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class UsingCollectionOperator {

    public static void main(String[] args) {
//        usingToList();
//        usingToSortedList();
//        usingToMap();
        usingToMultiMap();
//        usingCollectOperator();
    }

    public static void usingToList() {
        // 1st sample
//        Observable.range(1, 100)
//                .toList(100)
//                .subscribe(s -> System.out.println("Received: " + s));

        // 2nd sample
        Observable.just("Beta", "Gamma", "Alpha")
                .toList(CopyOnWriteArrayList::new)
                .subscribe(s -> System.out.println("Received: " + s.getClass().getName()));
    }

    public static void usingToSortedList() {
        Observable.just("Beta", "Gamma", "Alpha")
                .toSortedList()
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public static void usingToMap() {
        // 1st sample: create a custom key of hashmap
//        Observable.just("Beta", "Gamma", "Alpha")
//                .toMap(s -> s.charAt(0))
//                .subscribe(s -> System.out.println("Received: " + s + " with " + s.getClass().getName()));

        // 2nd sample: create a custom key-value of hashmap
//        Observable.just("Beta", "Gamma", "Alpha")
//                .toMap(s -> s.charAt(0), String::length)
//                .subscribe(s -> System.out.println("Received: " + s + " with " + s.getClass().getName()));

        // 3rd sample:
        Observable.just("Beta", "Gamma", "Alpha")
                .toMap(s -> s.charAt(0), String::length, ConcurrentHashMap::new)
                .subscribe(s -> System.out.println("Received: " + s + " with " + s.getClass().getName()));
    }

    public static void usingToMultiMap() {
        Observable.just("Beta", "Gamma", "Alpha")
                .toMultimap(String::length)
                .subscribe(s -> System.out.println("Received: " + s + " with " + s.getClass().getName()));
    }

    public static void usingCollectOperator() {
        Observable.just("Beta", "Gamma", "Alpha")
                .collect(HashSet::new, HashSet::add)
                .subscribe(s -> System.out.println("Received: " + s + " with " + s.getClass().getName()));
    }

}
