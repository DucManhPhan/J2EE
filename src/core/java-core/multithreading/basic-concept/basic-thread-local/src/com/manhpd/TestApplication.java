package com.manhpd;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TestApplication {

    public static void main(String[] args) {
//        usingListIterator();
//        usingIterator();
        usingReactiveStream();
    }

    public static void usingListIterator() {
        List<String> strings = Arrays.asList("Hello, ", "world", "!");

        for (ListIterator<String> iter = strings.listIterator(); iter.hasNext(); ) {
            String elem = iter.next();
            System.out.println(elem);
        }
    }

    public static void usingIterator() {
        List<String> strings = Arrays.asList("Hello, ", "world", "!");

        for (Iterator<String> iter = strings.iterator(); iter.hasNext(); ) {
            String elem = iter.next();
            System.out.println(elem);
        }
    }

    public static void usingStreamApi() {
        List<String> strings = Arrays.asList("Hello, ", "world", "!");
        strings.stream().forEach(str -> System.out.println(str));
    }

    public static void usingReactiveStream() {
        List<String> strings = Arrays.asList("Hello, ", "world", "!");
        Observable<String> observable = Observable.fromIterable(strings);
        observable.doOnNext(str -> System.out.println("Emitted: " + str))
                .doOnComplete(() -> System.out.println("Completed"))
                .subscribe(str -> System.out.println(str));
    }
}
