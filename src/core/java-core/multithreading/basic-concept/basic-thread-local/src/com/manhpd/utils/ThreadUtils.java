package com.manhpd.utils;

public class ThreadUtils {

    public static void sleep(int timeoutMs) {
        try {
            Thread.sleep(timeoutMs);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

}
