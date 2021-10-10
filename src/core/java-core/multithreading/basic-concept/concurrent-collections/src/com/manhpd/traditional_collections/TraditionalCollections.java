package com.manhpd.traditional_collections;

import java.util.Hashtable;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TraditionalCollections {

    public static void main(String[] args) {

    }

    public static void usingStack() {
        Stack<Integer> stk = new Stack<>();

        // initialize new thread
        Runnable task = () -> {
            stk.push(9);
            stk.push(10);
            stk.push(11);
        };

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(task);

        stk.push(0);
        stk.push(3);
        stk.push(6);

        executor.shutdown();
    }

    public static void usingHashTable() {
        Hashtable<Integer, String> tbl = new Hashtable<>();
        tbl.put(0, "first");

        CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList<>();
    }

}
