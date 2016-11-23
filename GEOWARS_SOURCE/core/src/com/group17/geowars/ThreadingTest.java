package com.group17.geowars;

/**
 * Created by nikola on 15/11/2016.
 */
public class ThreadingTest implements Runnable {
    private Thread t;
    private String threadName;

    ThreadingTest( String name) {
        threadName = name;
        System.out.println("Creating " +  threadName );
    }

    public void run() {
        System.out.println("Running " +  threadName );
            if (threadName=="Thread 1"){
                System.out.println("dit is threads 1");
                Integer temp1 = 0;
                while (1!=2){

                    System.out.println("thread1 "+temp1);
                }

            }else if (threadName=="Thread 2"){
                System.out.println("dit is threads 2");
                Integer temp2 = 0;
                while (1!=2){
                    temp2+= 1;
                    System.out.println("thread2 "+temp2);
                }
            }

        System.out.println("Thread " +  threadName + " exiting.");
    }

    public void start () {
        System.out.println("Starting " +  threadName );
        if (t == null) {
            t = new Thread (this, threadName);
            t.start ();
        }
    }
}
