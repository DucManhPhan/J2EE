package com.manhpd.operators;

import com.manhpd.utils.ReactiveUtils;
import io.reactivex.rxjava3.core.Observable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class UsingUtilityOperator {

    public static void main(String[] args) {
//        usingDelayOperator();
//        usingRepeatOperator();
//        usingSingle();
//        usingTimestamp();
        usingTimeInterval();
    }

    public static void usingDelayOperator() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM:ss");
        System.out.println(LocalDateTime.now().format(f));
        Observable.just("Alpha", "Beta", "Gamma")
                .delay(3, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println(LocalDateTime.now()
                        .format(f) + " Received: " + s));

        ReactiveUtils.sleep(5000);
    }

    public static void usingRepeatOperator() {
        Observable.just("Alpha", "Beta", "Gamma")
                .repeat(2)
                .subscribe(s -> System.out.println("Received: " + s));
    }

    public static void usingSingle() {
//        Observable.just("One")
        Observable.empty()
                .single("Four")
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public static void usingTimestamp() {
        Observable.just("One", "Two", "Three")
                .timestamp(TimeUnit.SECONDS)
                .subscribe(i -> System.out.println("Received: " + i));
    }

    public static void usingTimeInterval() {
        Observable.interval(2, TimeUnit.SECONDS)
                .doOnNext(i -> System.out.println("Emitted: " + i))
                .take(3)
                .timeInterval(TimeUnit.SECONDS)
                .subscribe(i -> System.out.println("Received: " + i));

        ReactiveUtils.sleep(7000);
    }

}
