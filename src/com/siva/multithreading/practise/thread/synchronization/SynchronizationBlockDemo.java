package com.siva.multithreading.practise.thread.synchronization;

import java.util.*;

/**
 * When we use synchronized(this), this will put a lock on the object to which that method belongs to.
 * just similar to synchronized method.If there are multiple synchronous methods in a class, at a time
 * only one thread can enter the method even though the other methods are independent. The advantage of
 * synchronized block is it will provide to lock on object that is specified in the parenthesis.
 */

class Display{

    Map<String,String> map;

    Display(Map<String,String> map){
        this.map = map;
    }

    public  void readMap() throws InterruptedException {
        synchronized (this){
            System.out.println("Reading Thread Acquired Lock");
            System.out.println("Reading was starting by "+Thread.currentThread().getName());
            for(int i=0;i<100;i++)
            {
                map.put(((Integer) i).toString(),((Integer) (i+100)).toString());
            }
            map.put("test","test123");
            System.out.println(map.toString());
        }
    }

    public void writingMap()  {

        synchronized (this){
            System.out.println("Writing Thread Acquired Lock");
            map.put("siva1","11");
            map.put("siva2","22");
            map.put("siva3","33");
            map.put("siva4","44");
            map.put("siva5","55");
            map.put("siva6","66");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Starting to write into Map::"+Thread.currentThread().getName());
            System.out.println("Entering to write into Map::"+Thread.currentThread().getName());
            map.put("siva"+new Date().toString(),"11 "+Thread.currentThread().getName());
            map.put("siva"+ new Date().toString(),"12 "+Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName()+":: "+map.toString());
        }
    }
}

class Runner3 implements Runnable{

    Display display;

    Runner3(Display d3){
        this.display = d3;
    }

    public void run()
    {
        display.writingMap();
//        try {
//            display.readMap();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}

class Runner4 implements Runnable{

    Display display;

    Runner4(Display display){
        this.display = display;
    }

    public void run()
    {
//        display.writingMap();
        try {
            display.readMap();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

public class SynchronizationBlockDemo {

    public static void main(String args[]) throws InterruptedException {

        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        map.put("siva1","1");
        map.put("siva2","2");
        map.put("siva3","3");
        map.put("siva4","4");
        map.put("siva5","5");
        map.put("siva6","6");
        map.put("siva7","7");
        map.put("siva8","8");
        map.put("siva9","9");
        map.put("siva10","10");

//        Map<String,String> smap = Collections.synchronizedMap(map);

        Display display = new Display(map);

        Thread t1 = new Thread(new Runner3(display));
        Thread t2 = new Thread(new Runner4(display));

        t1.setName("T1");
        t2.setName("T2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }

}
