package com.piyush.multithreading;

import java.util.Scanner;

/*
Both wait and notify can only be called inside the synchronized block. They are methods of the object class.
 */
class Processor4 {
  public void produce() throws InterruptedException {
    synchronized (this){
      System.out.println("Producer thread running...");
      /* Wait relinquishes the lock indefinitely and waits for a notify call. Wait is resource friendly
      unlike a empty while waiting for the condition to update. The other thread can acquire the lock after
      wait is executed.
       */
      wait();
      System.out.println("Producer thread resumed");
    }
  }

  public void consume() throws InterruptedException {
    Thread.sleep(2000); // Because we want the producer thread to run first in this example.
    Scanner scanner = new Scanner(System.in);

    synchronized (this){
      System.out.println("Waiting for return key press");
      scanner.nextLine();
      System.out.println("Return key pressed");
      notify(); // Will notify the threads waiting on this object that they will now be able to acquire a lock on it.
      Thread.sleep(5000); /* But the lock is not released till the entire synchronized block is executed.
      Hence the producer thread will have to wait an 5 seconds even though notify was called in the previous step. */
    }
  }
}

public class WaitAndNotify {

  public static void main(String ... args) throws InterruptedException {

    Processor4 processor = new Processor4();

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          processor.produce();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          processor.consume();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();
  }

}
