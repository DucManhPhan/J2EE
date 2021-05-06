package com.manhpd;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadId {

//    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> 0);

//    private static final ThreadLocal<Integer> threadId = new ThreadLocal<>();

    public static int get() {
        return threadId.get();
    }

    public static void remove() {
        threadId.remove();
    }

    public static void increment() {
        int current = threadId.get();
        threadId.set(++current);
    }

}
