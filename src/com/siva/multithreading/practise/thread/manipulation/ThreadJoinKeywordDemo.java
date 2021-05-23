package com.siva.multithreading.practise.thread.manipulation;

class Runner1 implements Runnable{
    public void run()
    {
        for(int i=0;i<10;i++)
        System.out.println("Runner1: "+i);
    }
}

class Runner2 implements Runnable{
    public void run(){
        for(int i=0;i<10;i++)
            System.out.println("Runner2: "+i);
    }
}

public class ThreadJoinKeywordDemo {

    public static void main(String args[]) throws InterruptedException {
        Thread t1 = new Thread(new Runner1());
        Thread t2 = new Thread(new Runner2());
        t1.start();
        t2.start();

        t1.join();//Current Thread(Main Thread) waits until t1 completes
        t2.join();//Current Thread(Main Thread) waits until t2 completes

        System.out.println("Finished main Thread");
    }

}
