package com.manhpd.join_method;


/**
 * join() method allows one thread to wait for the completion of another.
 *
 */
public class UsingJoinMethodApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Start main thread.");

        Thread firstThread = new Thread(() -> {
            System.out.println("First thread is running.");
        });

        Thread secondThread = new Thread(() -> {
            System.out.println("Second thread is running.");
        });

        secondThread.start();;
        firstThread.start();

        secondThread.join();
        firstThread.join();

        System.out.println("End main thread.");
    }

}
