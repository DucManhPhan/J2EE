package com.manhpd;

import java.util.concurrent.Exchanger;

public class LittleSisterThread implements Runnable {

    private Exchanger<Integer> exchanger;

    public LittleSisterThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println("Passing information from LittleSisterThread.");
            Integer exchange = exchanger.exchange(2);
            System.out.println("Information sent from LittleSisterThread.");
            System.out.println("Receive information from BrotherThread: " + exchange);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
