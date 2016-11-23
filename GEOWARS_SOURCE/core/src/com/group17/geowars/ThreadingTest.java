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
        try {
            for(int i = 4; i > 0; i--) {
                Thread.sleep(50);
                System.out.println("Thread: " + threadName + ", " + i);
                // Let the thread sleep for a while.
            }
        }catch (InterruptedException e) {
            System.out.println("Thread " +  threadName + " interrupted.");
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
