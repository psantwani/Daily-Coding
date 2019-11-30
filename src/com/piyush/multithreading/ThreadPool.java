package com.piyush.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor2 implements Runnable{

  private int id;

  public Processor2(int id){
    this.id = id;
  }

  @Override
  public void run() {
    System.out.println("Starting: " + id);
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Completed: " + id);
  }
}

public class ThreadPool {

  public static void main(String ... args){

    // Creates a pool of 2 threads.
    ExecutorService executor = Executors.newFixedThreadPool(2);

    /* Submits 5 tasks to the executor. The executor assigns any available task to an idle thread,
    This avoids the overhead of recycling the threads from the programmer's perspective.
     */
    for(int i = 0; i < 5; i++){
      // Submitting a task.
      executor.submit(new Processor2(i));
    }

    // Will shutdown/terminate the executor after all tasks have been executed by the 2 threads.
    executor.shutdown();

    // Will be printed immediately.
    System.out.println("All tasks submitted.");

    try {
      // Will wait for the termination of the executor or timeout after 1 day.
      executor.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Will be printed only after executor is terminated.
    System.out.println("All tasks completed.");

  }

}
