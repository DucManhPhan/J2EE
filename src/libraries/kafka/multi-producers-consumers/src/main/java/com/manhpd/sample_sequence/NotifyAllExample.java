package com.manhpd.sample_sequence;

public class NotifyAllExample {

    int status=1;
    public static void main(String[] args) {
        NotifyAllExample notifyAllExample = new NotifyAllExample();

        A1 a=new A1(notifyAllExample);
        B1 b=new B1(notifyAllExample);
        C1 c=new C1(notifyAllExample);

        a.start();
        b.start();
        c.start();
    }
}

class A1 extends Thread{
    NotifyAllExample notifyAllExample;

    A1(NotifyAllExample notifyAllExample){
        this.notifyAllExample = notifyAllExample;
    }

    @Override
    public void run() {

        try{
            synchronized (notifyAllExample) {
//                for (int i = 0; i < 100; i++) {
                while (true) {
                    while (notifyAllExample.status != 1) {
                        notifyAllExample.wait();
                        System.out.println("A is still running in wait()");
                    }

                    System.out.print("A ");
                    notifyAllExample.status = 2;
                    notifyAllExample.notifyAll();

                    System.out.println("A is still running");
                }

            }
        }catch (Exception e) {
            System.out.println("Exception 1 :"+e.getMessage());
        }

    }

}

class B1 extends Thread{

    NotifyAllExample notifyAllExample;

    B1(NotifyAllExample notifyAllExample){
        this.notifyAllExample = notifyAllExample;
    }

    @Override
    public void run() {

        try{
            synchronized (notifyAllExample) {
//                for (int i = 0; i < 100; i++) {
                while (true) {
                    while (notifyAllExample.status!=2) {
                        notifyAllExample.wait();
                        System.out.println("B is still running in wait()");
                    }

                    System.out.print("B ");
                    notifyAllExample.status = 3;
                    notifyAllExample.notifyAll();
                    System.out.println("B is still running");
                }

            }
        }catch (Exception e) {
            System.out.println("Exception 2 :"+e.getMessage());
        }

    }
}


class C1 extends Thread{

    NotifyAllExample notifyAllExample;

    C1(NotifyAllExample notifyAllExample){
        this.notifyAllExample = notifyAllExample;
    }

    @Override
    public void run() {
        try{
            synchronized (notifyAllExample) {
//                for (int i = 0; i < 100; i++) {
                while (true) {
                    while (notifyAllExample.status!=3) {
                        notifyAllExample.wait();
                        System.out.println("C is still running in wait()");
                    }

                    System.out.print("C ");
                    notifyAllExample.status = 1;
                    notifyAllExample.notifyAll();
                    System.out.println("C is still running");
                }

            }
        }catch (Exception e) {
            System.out.println("Exception 3 :"+e.getMessage());
        }

    }
}
