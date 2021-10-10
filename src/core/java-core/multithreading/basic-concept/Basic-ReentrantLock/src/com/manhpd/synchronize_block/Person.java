package com.manhpd.synchronize_block;

public class Person {

    private String name;

    private String address;

    private Object lockObject;

    private String nameOfThread;

    private int sleepTime;

    public static int agePerson;

    public Person(Object lockObject) {
        this.lockObject = lockObject;
    }

    public void setAge(int age) throws InterruptedException {
        System.out.println("Address of lock object is " + this.lockObject);
        synchronized (this.lockObject) {
            System.out.println("Step into synchronized block of " + this.nameOfThread);
            agePerson = age;
            System.out.println("Currently, the age of person is: " + agePerson + " of " + this.nameOfThread);
            Thread.sleep(this.sleepTime);
            System.out.println("The end of sleeping time in " + this.nameOfThread);
        }
    }

    public void setNameOfThread(String nameOfThread) {
        this.nameOfThread = nameOfThread;
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

}
