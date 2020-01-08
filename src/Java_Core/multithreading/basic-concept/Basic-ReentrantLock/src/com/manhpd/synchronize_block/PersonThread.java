package com.manhpd.synchronize_block;

public class PersonThread implements Runnable {

    private Object lockObject;

    private String nameOfThread;

    private int sleepTime;

    public PersonThread(Object lockObject, String nameOfThread, int sleepTime) {
        this.lockObject = lockObject;
        this.nameOfThread = nameOfThread;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        System.out.println("In " + this.nameOfThread);
        Person mary = new Person(this.lockObject);
        mary.setNameOfThread(this.nameOfThread);
        mary.setSleepTime(this.sleepTime);
        try {
            mary.setAge(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
