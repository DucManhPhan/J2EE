package com.manhpd;



public class Application {

    private static int counter = 0;

    private static synchronized void increment() {
        counter += 1;
    }

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Start main thread.");

        Thread firstThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; ++i) {
                    increment();
                }
            }
        });

        Thread secondThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; ++i) {
                    increment();
                }
            }
        });

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();

        System.out.println("Counter: " + counter);
        System.out.println("End main thread.");
    }

}
