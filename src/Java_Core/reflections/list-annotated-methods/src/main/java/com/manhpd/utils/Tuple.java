package com.manhpd.utils;

public class Tuple<T, Q> {

    private T first;

    private Q second;

    public Tuple(T first, Q second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public Q getSecond() {
        return this.second;
    }
}
