package com.manhpd.using_wait_notify;

import java.util.stream.IntStream;

public class ProducerConsumer {

    private static int[] buffer;

    private static int count;

    private static Object lock = new Object();

    static class Producer {
        void produce() throws InterruptedException {
            synchronized (lock) {
                while (isFull(buffer)) {
                    lock.wait();
                }

                buffer[count++] = 1;
                System.out.println("In the producer, count = " + count);
                lock.notifyAll();
            }
        }
    }

    static class Consumer {
        void consumer() throws InterruptedException {
            synchronized (lock) {
                while (isEmpty(buffer)) {
                    lock.wait();
                }

                buffer[--count] = 0;
                System.out.println("In the consumer, count = " + count);

                lock.notifyAll();
            }
        }
    }

    static boolean isEmpty(int[] buffer) {
        return count == 0;
    }

    static boolean isFull(int[] buffer) {
        return count == buffer.length;
    }

    public static void main(String[] args) throws InterruptedException {
        buffer = new int[10];
        count = 0;

        final Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Runnable produceTask = () -> {
            for (int i = 0; i < 50; ++i) {
                try {
                    producer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("Done producing");
        };

        Runnable consumeTask = () -> {
            for (int i = 0; i < 50; ++i) {
                try {
                    consumer.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
