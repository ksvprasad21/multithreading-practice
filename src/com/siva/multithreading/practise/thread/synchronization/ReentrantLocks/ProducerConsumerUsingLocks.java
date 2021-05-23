package com.siva.multithreading.practise.thread.synchronization.ReentrantLocks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ProducerConsumer {

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void producer(){

        lock.lock();

        System.out.println("Producer");
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Again Producer");

        lock.unlock();
    }

    public void consumer(){
        lock.lock();

        System.out.println("Consumer");
        condition.signal();

        lock.unlock();
    }

}

public class ProducerConsumerUsingLocks{

    public static void main(String args[]){

        ProducerConsumer producerConsumer = new ProducerConsumer();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                producerConsumer.producer();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                producerConsumer.consumer();
            }
        });

        t1.start();
        t2.start();

    }

}
