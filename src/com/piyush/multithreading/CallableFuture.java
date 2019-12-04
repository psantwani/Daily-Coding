package com.piyush.multithreading;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFuture {
  public static void main(String ... args){
    ExecutorService executor = Executors.newFixedThreadPool(10);
    Future<Integer> future = executor.submit(new Task());

    try {
      Integer result = future.get(); // blocking.
      System.out.println(result);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    executor.shutdown();
  }

  static class Task implements Callable<Integer>{
    @Override
    public Integer call(){
      return new Random().nextInt(100);
    }
  }
}