package com.piyush.multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker2 implements Runnable{

  CountDownLatch latch;

  public Worker2(CountDownLatch latch){
    this.latch = latch;
  }

  public void run(){
    System.out.println("Started..");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    latch.countDown();
  }
}

public class CountDownLatchExample {

  public static void main(String ... args){

    /*
    Create a countDownLatch. Its a thread-safe class.
    CountDownLatch can be used instead of Thread.Join() method when using ExecutorService.
    It basically waits for no. of Threads to finish as defined the Latch before proceeding ahead.
     */
    CountDownLatch latch = new CountDownLatch(5);
    ExecutorService executor = Executors.newFixedThreadPool(3);

    for(int i = 0; i < 5; i++){
      executor.submit(new Worker2(latch));
    }

    System.out.println("All tasks submitted.");

    try {
      // Waiting for the latch to go down to 0;
      latch.await();
      executor.shutdown();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("All tasks completed");

  }
}
