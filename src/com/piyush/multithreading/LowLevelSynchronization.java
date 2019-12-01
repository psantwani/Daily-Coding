package com.piyush.multithreading;

import java.util.LinkedList;
import java.util.Random;

class Processor5 {

  LinkedList<Integer> linkedList = new LinkedList<>();
  int MAX_LIMIT = 10;
  Object lock = new Object();

  public void produce() throws InterruptedException {
    int value = 0;

    while (true){
      synchronized (lock){
        while (linkedList.size() == MAX_LIMIT){
          lock.wait();
        }
        linkedList.add(value++);
        lock.notify();
      }
    }
  }

  public void consume() throws InterruptedException {
    Random random = new Random();
    while (true){
      synchronized (lock){
        while (linkedList.size() == 0){
          lock.wait();
        }
        System.out.print("List size: " + linkedList.size());
        int value = linkedList.removeFirst();
        System.out.println("; Value : " + value);
        lock.notify();
        Thread.sleep(random.nextInt(1000));
      }
    }
  }
}

public class LowLevelSynchronization {

  public static void main(String ... args) throws InterruptedException {

    Processor5 processor = new Processor5();

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
