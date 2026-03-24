package org.example.Threading;

class Reserve implements Runnable{

    int avialable = 1;
    int wanted ;
    Reserve(int i ){
        wanted = i;
    }
    @Override
    public void run() {
        //unSafe

//        System.out.println("Available berths = " +avialable);
//
//        if(avialable >= wanted){
//            String name = Thread.currentThread().getName();
//            System.out.println(wanted + " Breach Reserved for " +  name);
//            try{
//                Thread.sleep(2000);
//                avialable -=  wanted;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }else {
//            System.out.println("not seat available");
//        }
        synchronized (this){
            System.out.println("Available berths = " +avialable);

            if(avialable >= wanted){
                String name = Thread.currentThread().getName();
                System.out.println(wanted + " Breach Reserved for " +  name);
                avialable -= wanted;
            }else {
                System.out.println("not seat available");
            }
        }
    }
}


public class TicketBooking {
    public static void main(String[] args){
        Reserve obj = new Reserve(1);
        Thread t1 = new Thread(obj);
        Thread t2 = new Thread(obj);
        t1.setName("Chetan");
        t2.setName("Suraj");
        t1.start();
        t2.start();

    }

}
