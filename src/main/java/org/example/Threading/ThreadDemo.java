package org.example.Threading;

//example of a thread
class MyThread extends Thread {
    public void run(){
        for(int i = 1; i < 100; i++){
            System.out.println(i);
        }
    }
}

//To create a thread and run it and terminating by pressing the enter
class MyThread1 extends Thread {
    private volatile boolean stop = false;   // flag to stop thread safely

    public void run(){
        for(int i = 1; i < 100; i++){
            if(stop) return;   // stop execution gracefully
            System.out.println(i);
        }
    }

    // custom method to stop thread (instead of deprecated stop())
    public void stopThread(){
        stop = true;
    }
}

class ThreadDemo {
    public static void main(String[] args) throws Exception {
        MyThread myThread = new MyThread();
        myThread.start();   // no need to wrap in Thread again

        //Terminate thread
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();   // fixed (was t.start() earlier)

        System.in.read();   // press Enter to stop

        myThread1.stopThread();   // safe stop (not deprecated)
    }
}