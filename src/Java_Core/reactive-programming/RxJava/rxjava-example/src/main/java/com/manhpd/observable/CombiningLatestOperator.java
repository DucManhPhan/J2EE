package com.manhpd.observable;

import com.manhpd.utils.ReactiveUtils;
import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

public class CombiningLatestOperator {

    public static void main(String[] args) {
        usingCombineLatest();
    }

    public static void usingCombineLatest() {
        Observable<Long> source1 = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS);

        Observable.combineLatest(source1, source2, (l1, l2) -> "SOURCE 1: " + l1 + " SOURCE 2: " + l2)
                .subscribe(System.out::println);

        ReactiveUtils.sleep(3000);
    }

}
