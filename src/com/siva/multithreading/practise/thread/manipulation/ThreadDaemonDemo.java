package com.siva.multithreading.practise.thread.manipulation;

/**
 *
 * Once the normal thread finishes, the daemon threads will automatically
 * get killed or terminated.The following example illustrates that.
 *
 */

class NormalWorker implements Runnable{
    public void run()
    {
        try {
            Thread.sleep(1111);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("NormalWorker finished execution..");
    }
}

class DaemonWorker implements Runnable{
    public void run(){

        try {
            Thread.sleep(9999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("DaemonWorker finished execution..");
    }
}

public class ThreadDaemonDemo {

    public static void main(String args[]){

        Thread t1 = new Thread(new NormalWorker());
        Thread t2 = new Thread(new DaemonWorker());

        t1.setDaemon(false); //Default value itself also false
        t2.setDaemon(true);

        t1.start();
        t2.start();

    }

}
