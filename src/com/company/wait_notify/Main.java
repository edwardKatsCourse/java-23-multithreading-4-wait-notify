package com.company.wait_notify;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //producer - consumer
        //factory - customer

        Object lock = new Object();

        synchronized (lock) {
            //thread 1

            //1. blocking call, 2. on wait call - synchronized releases monitor lock
            lock.wait(3000);
            //... more code ...
            //end thread 1

            //thread 2
            //lock.notify();
            //new Object().notify()
            //end thread 2
        }

        System.out.println("get me out of here!!!");
    }
}
