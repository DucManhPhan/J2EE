package com.manhpd;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        OtherObject otherObject = new OtherObject();
        Runnable r1 = () -> otherObject.a();
        Runnable r2 = () -> otherObject.b();

        Thread t1 = new Thread(r1);
        t1.start();

        Thread t2 = new Thread(r2);
        t2.start();

        t1.join();
        t2.join();
    }

}
