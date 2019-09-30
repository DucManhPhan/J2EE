package com.manhpd.reactor.mono;

import reactor.core.publisher.Mono;

public class MonoUtils {

    public static void main(String[] args) {
        Mono.just("hello, world!").subscribe(v -> System.out.println(v));
    }

}
