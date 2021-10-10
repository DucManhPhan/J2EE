package com.manhpd;

import java.util.concurrent.Exchanger;

public class BrotherThread implements Runnable {

    private Exchanger<Integer> exchanger;

    public BrotherThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println("Passing information from BrotherThread.");
            Integer exchange = exchanger.exchange(100);
            System.out.println("Information sent from BrotherThread.");
            System.out.println("Receive information from LittleSisterThread: " + exchange);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
