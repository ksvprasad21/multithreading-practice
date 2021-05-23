package com.siva.multithreading.practise.thread.creation;

class Runner3 extends Thread{
    public void run(){
        for(int i=0;i<10;i++)
            System.out.println("Runner3: "+i);
    }
}

class Runner4 extends Thread{
    public void run(){
        for(int i=0;i<10;i++)
            System.out.println("Runner4: "+i);
    }
}


public class StartingThreadswithThreadClass {

    public static void main(String args[]){

        Runner3 runner3 = new Runner3();
        Runner4 runner4 = new Runner4();

        runner3.start();
        runner4.start();

    }

}
