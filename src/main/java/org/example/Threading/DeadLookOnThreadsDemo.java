package org.example.Threading;

class Train {
    private int sleeperSeats = 5;
    private int acSeats = 3;

    // booking method (synchronized)
    public synchronized void bookSeat(String name, String compartment) {
        if (compartment.equalsIgnoreCase("sleeper")) {
            if (sleeperSeats > 0) {
                System.out.println(name + " booked Sleeper seat");
                sleeperSeats--;
            } else {
                System.out.println("No Sleeper seats available for " + name);
            }
        } else if (compartment.equalsIgnoreCase("ac")) {
            if (acSeats > 0) {
                System.out.println(name + " booked AC seat");
                acSeats--;
            } else {
                System.out.println("No AC seats available for " + name);
            }
        }
    }

    // cancel method (synchronized)
    public synchronized void cancelSeat(String name, String compartment) {
        if (compartment.equalsIgnoreCase("sleeper")) {
            sleeperSeats++;
            System.out.println(name + " cancelled Sleeper seat");
        } else if (compartment.equalsIgnoreCase("ac")) {
            acSeats++;
            System.out.println(name + " cancelled AC seat");
        }
    }
}

// Booking Thread
class BookingThread implements Runnable {
    Train train;
    String name;
    String compartment;

    BookingThread(Train train, String name, String compartment) {
        this.train = train;
        this.name = name;
        this.compartment = compartment;
    }

    public void run() {
        train.bookSeat(name, compartment);
    }
}

// Cancellation Thread
class CancelThread implements Runnable {
    Train train;
    String name;
    String compartment;

    CancelThread(Train train, String name, String compartment) {
        this.train = train;
        this.name = name;
        this.compartment = compartment;
    }

    public void run() {
        train.cancelSeat(name, compartment);
    }
}

public class DeadLookOnThreadsDemo {
    public static void main(String[] args) {

        Train train = new Train();

        // booking threads
        Thread t1 = new Thread(new BookingThread(train, "Chetan", "Sleeper"));
        Thread t2 = new Thread(new BookingThread(train, "Rahul", "Sleeper"));
        Thread t3 = new Thread(new BookingThread(train, "Amit", "AC"));

        // cancellation thread
        Thread t4 = new Thread(new CancelThread(train, "Rahul", "Sleeper"));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}