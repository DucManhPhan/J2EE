package com.manhpd.using_blocking_queue;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Example:
 * https://dzone.com/articles/concurrency-pattern-producer
 *
 * https://scratchpad101.com/2011/08/22/concurrency-pattern-producer-consumer/
 *
 * Disadvantage:
 * - The continueProducing variable of Broker class is not volatile variable. So, when using multithreading, reading value of it is not correct.
 * - Use Future functionality will block our main thread --> reduce performance of system.
 */
public class HighThroughputMain {

    public static void start() {
        try {
            Broker broker = new Broker();
            ExecutorService threadPool = Executors.newFixedThreadPool(3);
            threadPool.execute(new Consumer("1", broker));
            threadPool.execute(new Consumer("2", broker));

            Future producerStatus = threadPool.submit(new Producer(broker));
            producerStatus.get();

            threadPool.shutdown();;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
