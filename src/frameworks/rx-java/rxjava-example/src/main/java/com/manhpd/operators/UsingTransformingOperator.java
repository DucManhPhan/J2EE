package com.manhpd.operators;

import io.reactivex.rxjava3.core.Observable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class UsingTransformingOperator {

    public static void main(String[] args) {
//        usingMap();
//        usingCast();
//        usingStartWithItem();
//        usingStartWithArray();
//        usingStartWithIterable();
//        usingConCatWith();
//        usingSorted();
//        usingSortedWithComparator();
        usingScan();
    }

    public static void usingMap() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
        Observable.just("1/3/2016", "5/9/2016", "10/12/2016")
                .map(s -> LocalDate.parse(s, dtf))
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public static void usingCast() {
        Observable.just("alpha", "beta", "gamma")
                .cast(Object.class)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public static void usingStartWithItem() {
        Observable<String> menu = Observable.just("coffee", "tea", "espresso", "latte");
        menu.startWithItem("SHOP MENU")
                .subscribe(System.out::println);
    }

    public static void usingStartWithArray() {
        Observable<String> menu = Observable.just("coffee", "tea", "espresso", "latte");
        menu.startWithArray("SHOP MENU", "---------------")
                .subscribe(System.out::println);
    }

    public static void usingStartWithIterable() {
        List<String> prefixMenu = Arrays.asList("SHOP MENU", "---------------");
        Observable<String> menu = Observable.just("coffee", "tea", "espresso", "latte");
        menu.startWithIterable(prefixMenu)
                .subscribe(System.out::println);
    }

    public static void usingConCatWith() {
        Observable<String> prefixMenu = Observable.just("SHOP MENU", "---------------");
        Observable<String> menu = Observable.just("coffee", "tea", "espresso", "latte");
        prefixMenu.concatWith(menu)
                .subscribe(System.out::println);
    }

    public static void usingSorted() {
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
            .sorted()
            .reduce("", (append, value) -> append + ", " + value)
            .subscribe(System.out::print);
    }

    public static void usingSortedWithComparator() {
        // 1st sample
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .sorted(Comparator.reverseOrder())
                .reduce("", (append, value) -> append + ", " + value)
                .subscribe(System.out::print);

        // 2nd sample
//        Observable.just("alpha", "beta", "gamma")
//                .sorted(Comparator.comparingInt(String::length))
//                .subscribe(System.out::println);
    }

    public static void usingScan() {
        // 1st sample
//        Observable.just(5, 3, 7)
//                .scan((accumulator, i) -> accumulator + i)
//                .subscribe(s -> System.out.println("Received: " + s));

        // 2nd sample
        Observable.just("alpha", "beta", "gamma")
                .scan(0, (total, next) -> total + 1)
                .skip(1)
                .subscribe(s -> System.out.println("Received: " + s));
    }
}
