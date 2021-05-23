package com.siva.multithreading.practise.thread.synchronization.communication;

import java.util.ArrayList;
import java.util.List;

/**
 * Here we are using a Separetae Object lock for allowing two threads
 * to access a common object
 */

class ProcessorForCustomLock {

    private List<Integer> list = new ArrayList<>();
    private final int LIMIT = 5;
    private final int BOTTOM = 0;
    private final Object lock = new Object();
    private int value = 0;

    public void producer() throws InterruptedException {

        synchronized (lock) {

            while(true) {

                if( list.size() == LIMIT ) {
                    System.out.println("Waiting for removing items from the list...");
                    lock.wait();
                } else {
                    System.out.println("Adding: "+value);
                    list.add(value);
                    value++;
                    lock.notify();

                    System.out.println("Calling notify from Producer on lock");
                }

                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {

        synchronized (lock) {

            while(true) {

                if( list.size() == BOTTOM ) {
                    System.out.println("Waiting for adding items to the list...");
                    lock.wait();
                } else {
                    System.out.println("Removed: "+list.remove(--value));
                    System.out.println("Calling notify from consumer on lock");
                    lock.notify();
                    // the notify method is not going to notify immediately
                    //because it will do further operations. Since it is in awhile loop
                    //it will iterate continuously.
                    /**
                     * So in the next iteration, we will remove the item we the value zero and then the list size is equal
                     *
                     * to zero, which means that the consumer threat is going to call the wave method on the lock.
                     *
                     * And because it is in a waiting state and we have already called the.
                     *
                     * Then the other thread as far as producer method is concerned, is going to be woken up and it is going
                     *
                     * to continue with the operation.
                     *
                     * What does it mean that it is going to OD a given value zero, then one, two, three and four.
                     *
                     * And you may pose the question again that, OK, here we go and notify.
                     *
                     * But again, this is what we have been talking about, that Java is not going to notify this thread immediately.
                     *
                     * It is going to do other operations before that.
                     *
                     * And when the size of the list is close to five, this is when a producer is going to wait.
                     *
                     * And because it has already called the notify, the other threat is going to be woken up again.
                     *
                     * And this is why there's going to be an oscillation like this that the first threat keeps all the items
                     */
                }

                Thread.sleep(500);
            }

        }

    }
}

public class ProducerConsumerUsingCustomLock {

    static ProcessorForCustomLock processorForCustomLock = new ProcessorForCustomLock();

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    processorForCustomLock.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    processorForCustomLock.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
