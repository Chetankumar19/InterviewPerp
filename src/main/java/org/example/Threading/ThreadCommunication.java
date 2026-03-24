package org.example.Threading;

public class ThreadCommunication {
    public static void main(String[] args){
        Producer producer = new Producer();
        Consumer consumer = new Consumer(producer);

        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);

        t2.start();
        t1.start();
    }
}

class Producer implements Runnable {
    StringBuffer sb;
    boolean dataprodover = false;

    Producer() {
        sb = new StringBuffer();
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 1; i < 10; i++) {
                sb.append(i).append(" ");
                System.out.println("appending..");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            dataprodover = true;

            notify();  // 🔥 notify consumer
        }
    }
}

class Consumer implements Runnable {
    Producer prod;

    public Consumer(Producer prod) {
        this.prod = prod;
    }

    @Override
    public void run() {
        synchronized (prod) {
            try {
                while (!prod.dataprodover) {
                    prod.wait();   // 🔥 wait instead of looping
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            System.out.println(prod.sb);
        }
    }
}