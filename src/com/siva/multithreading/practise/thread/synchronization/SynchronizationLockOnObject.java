package com.siva.multithreading.practise.thread.synchronization;

import java.util.stream.IntStream;

/**
 * When we use the synchronized keyword on method, lock will be placed on object.
 * Even though there is any other synchronized method which is not dependent
 * on this synchronized method, they cannot be run parallely by two threads since
 * the lock will be the first thread since lock is at object level.
 */

class Resource{

    public synchronized void getResource1(){
        System.out.println("Entered into getResource1() by::"+Thread.currentThread().getName());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        IntStream.range(0,10).forEach((i) -> {
            System.out.println("Printing "+i+" "+Thread.currentThread().getName());
        });
    }

    public synchronized void getResource2(){
        System.out.println("Entered into getResource1() by::"+Thread.currentThread().getName());
        IntStream.range(0,10).forEach((i) -> {
            System.out.println("Printing "+i+" "+Thread.currentThread().getName());
        });
    }

}

public class SynchronizationLockOnObject {

    public static void main(String args[]){

        Resource resource = new Resource();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                resource.getResource1();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                resource.getResource2();
            }
        });

        t1.setName("T1");;
        t2.setName("T2");

        t1.start();
        t2.start();

    }

}
