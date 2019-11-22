package com.manhpd;


import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The exchanger class provides a kind of point for two threads,
 * where threads can exchange their objects with other threads.
 *
 * An Exchanger may be viewed as a bidirectional form of a SynchronousQueue.
 * Exchangers may be useful in applications such as genetic algorithms and pipeline designs.
 *
 * When a thread arrives at an exchange point, it is necessary to wait for another thread to arrive.
 * When other partners come in threads, two threads go forward to exchange threads.
 */
public class Application {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new BrotherThread(exchanger));
        service.submit(new LittleSisterThread(exchanger));

        service.shutdown();
    }
}
