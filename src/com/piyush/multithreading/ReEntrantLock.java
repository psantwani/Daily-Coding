package com.piyush.multithreading;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Runner {
  private int count = 0;

  /* Its an alternative to synchronized. It implements the lock interface.
  Its called reentrant because, if a thread locks the lock, it can again lock it over and over again.
  The reentrant lock then keeps a count of the number of locks.
  The thread must then unlock it as many times, to allow another thread to then use the lock.
   */
  private Lock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();

  public void increment(){
    for(int i = 0; i < 1000; i++){
      count++;
    }
  }

  public void firstThread() throws InterruptedException{
    lock.lock(); // Acquires lock.

    System.out.println("First thread - Waiting...");
    condition.await(); // Similar to wait().

    System.out.println("First thread - Woken up...");

    try {
      System.out.println("First thread - Incrementing...");
      increment();
    }
    finally {
      System.out.println("First thread - Unlocking...");
      lock.unlock(); // Releases lock.
    }
  }

  public void secondThread() throws InterruptedException{
    Thread.sleep(1000);
    lock.lock();

    System.out.println("Waiting for return key..");
    new Scanner(System.in).nextLine();
    System.out.println("Got return key...");
    condition.signal(); // Similar to notify()

    try {
      System.out.println("Second thread - Incrementing...");
      increment();
    }
    finally {
      System.out.println("Second thread - Unlocking...");
      lock.unlock();
    }
  }

  public void finished(){
    System.out.println("Count is : " + count);
  }
}

public class ReEntrantLock {
  public static void main(String ... args){
    Runner runner = new Runner();

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          runner.firstThread();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          runner.secondThread();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    runner.finished();

  }
}
