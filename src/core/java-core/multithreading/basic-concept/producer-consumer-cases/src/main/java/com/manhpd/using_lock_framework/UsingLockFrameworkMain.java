package com.manhpd.using_lock_framework;


import com.manhpd.using_wait_notify.ProducerConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * Use lock framework: https://javarevisited.blogspot.com/2015/06/java-lock-and-condition-example-producer-consumer.html#axzz66cRUo3Xi
 */
public class UsingLockFrameworkMain {

    private static int[] buffer;

    private static int count;

    private static Lock lock = new ReentrantLock();

    private static Condition notFull = lock.newCondition();

    private static Condition notEmpty = lock.newCondition();

    static boolean isEmpty(int[] buffer) {
        return count == 0;
    }

    static boolean isFull(int[] buffer) {
        return count == buffer.length;
    }

    static class Producer {
        public void produce() {
            try {
                lock.lock();
                while (isFull(buffer)) {
                    notFull.await();
                }

                buffer[count++] = 1;
                notEmpty.signal();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            } finally {
                lock.unlock();
            }
        }
    }

    static class Consumer {
        public void consume() {
            try {
                lock.lock();
                while (isEmpty(buffer)) {
                    notEmpty.await();
                }

                buffer[--count] = 0;
                notFull.signal();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        buffer = new int[10];
        count = 0;

        final Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Runnable produceTask = () -> {
            for (int i = 0; i < 50; ++i) {
                producer.produce();
            }

            System.out.println("Done producing");
        };

        Runnable consumeTask = () -> {
            for (int i = 0; i < 50; ++i) {
                consumer.consume();
            }

            System.out.println("Done consuming");
        };

        Thread consumerThread = new Thread(consumeTask);
        Thread produceThread = new Thread(produceTask);

        consumerThread.start();
        produceThread.start();

        consumerThread.join();
        produceThread.join();

        System.out.println("Data in the buffer: " + count);

        IntStream.of(buffer).forEach(item -> System.out.println(item + " "));
    }

}
