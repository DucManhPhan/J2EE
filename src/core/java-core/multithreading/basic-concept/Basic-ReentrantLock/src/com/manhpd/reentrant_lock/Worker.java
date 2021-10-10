package com.manhpd.reentrant_lock;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

public class Worker implements Runnable {

    private String name;

    private ReentrantLock re;

    public Worker(String name, ReentrantLock re) {
        this.name = name;
        this.re = re;
    }

    @Override
    public void run() {
        boolean done = false;
        while (!done) {
            // Getting outer lock
            boolean ans = re.tryLock();

            // Returns True if lock is free
            if(ans) {
                try {
                    Date d = new Date();
                    SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                    System.out.println("task name - "+ name
                            + " outer lock acquired at "
                            + ft.format(d)
                            + " Doing outer work");
                    Thread.sleep(1500);

                    // Getting Inner Lock
                    re.lock();
                    try {
                        d = new Date();
                        ft = new SimpleDateFormat("hh:mm:ss");
                        System.out.println("task name - "+ name
                                + " inner lock acquired at "
                                + ft.format(d)
                                + " Doing inner work");
                        System.out.println("Lock Hold Count - "+ re.getHoldCount());
                        Thread.sleep(1500);
                    }
                    catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                    finally {
                        //Inner lock release
                        System.out.println("task name - " + name + " releasing inner lock");

                        re.unlock();
                    }

                    System.out.println("Lock Hold Count - " + re.getHoldCount());
                    System.out.println("task name - " + name + " work done");

                    done = true;
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
                finally
                {
                    //Outer lock release
                    System.out.println("task name - " + name +
                            " releasing outer lock");

                    re.unlock();
                    System.out.println("Lock Hold Count - " +
                            re.getHoldCount());
                }
            } else {
                System.out.println("task name - " + name + " waiting for lock");
                try {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
