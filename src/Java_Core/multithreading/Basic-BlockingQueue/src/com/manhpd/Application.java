package com.manhpd;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A Queue that additionally supports operations that wait for the queue to become non-empty when retrieving an element,
 * and wait for space to become available in the queue when storing an element.
 *
 * A BlockingQueue may be capacity bounded.
 * At any given time it may have a remainingCapacity beyond which no additional elements can be put without blocking.
 * A BlockingQueue without any intrinsic capacity constraints always reports a remaining capacity of Integer.MAX_VALUE.
 *
 * BlockingQueue implementations are thread-safe.
 * All queuing methods achieve their effects atomically using internal locks or other forms of concurrency control.
 * However, the bulk Collection operations addAll, containsAll, retainAll and removeAll are not necessarily performed atomically unless specified otherwise in an implementation.
 *
 * So it is possible, for example, for addAll(c) to fail (throwing an exception) after adding only some of the elements in c.
 *
 */
public class Application {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(5);
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(new Producer(blockingQueue));
        service.submit(new Consumer(blockingQueue));
        service.shutdown();
    }

}
