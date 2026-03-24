package org.example.Threading;

class MultiThread implements Runnable {
    String str;

    MultiThread(String str){
        this.str = str;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println(str + " " + i);
//            try {
//                Thread.sleep(2000);  // 2 seconds delay
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        }
    }
}

public class MultithreadingDemo {
    public static void main(String[] args) {

        MultiThread obj1 = new MultiThread("Cut the Ticket");
        MultiThread obj2 = new MultiThread("Show the Seat");

        Thread t1 = new Thread(obj1);
        Thread t2 = new Thread(obj2);

        t1.start();
        t2.start();
    }
}