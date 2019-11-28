package com.piyush.multithreading;

import java.util.Scanner;

class Processor extends Thread{

  /**
   * When Java runs a new thread, it caches/optimizes the data that is uses, because it assumes that
   * no outside code will update the data. But in this case, we are updating running from the main thread.
   * Hence, if we don't instruct Java to not cache, it is possible that it will never know when running becomes false
   * and it will keep running in infinite loop. Volatile keyword ensures that.
   */
  private volatile boolean running = true;

    @Override
    public void run(){
      while (running){
        System.out.println("Hello");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }

    public void stopRunning(){
      running = false;
    }
}

public class Volatile {
  public static void main(String ... args){
    Processor t1 = new Processor();
    t1.start();

    /*
      This will pause the main thread till you enter something.
     */
    System.out.println("Press any key to stop thread.");
    Scanner in = new Scanner(System.in);
    in.nextLine();

    t1.stopRunning();
  }
}
