package com.piyush.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class Connection{

  private static Connection instance = new Connection();
  private int connections = 0;

  /* Semaphores are used when you want to control any access. Here for example
  we only want to allow 10 connections at a time. Fair=true, makes sure that threads who requested
  first for the lock, are granted access first when they are waiting.
   */
  private Semaphore semaphore = new Semaphore(10, true);

  private Connection(){}

  public static Connection getConnection(){
    return instance;
  }

  public void connect() throws InterruptedException {
    try {
      semaphore.acquire(); // Decrements the permit count by 1.
      doConnect();
    }
    finally {
      semaphore.release(); // Increments the permit count by 1.
    }
  }

  private void doConnect() throws InterruptedException {

    synchronized (this){
      connections++;
      System.out.println("Connections : " + connections);
    }

    Thread.sleep(2000);

    synchronized (this){
      connections--;
    }
  }

}

public class SemaphoreExample {
  public static void main(String ... args){

    /*
    Creates a pool of threads, which are recycled based on availability.
     */
    ExecutorService executor = Executors.newCachedThreadPool();

    // Here we are creating 200 tasks at once, so 200 threads would be created (not the use case of re-usability).
    for(int i = 0; i < 200; i++){
      executor.submit(new Runnable() {
        @Override
        public void run() {
          try {
            Connection.getConnection().connect();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
    }

    executor.shutdown();
  }
}
