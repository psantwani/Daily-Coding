package com.piyush.multithreading;

/**
 * Creating a thread by extending the thread class and overriding run method.
 */
class One extends Thread {
  @Override
  public void run() {
    for(int i = 0; i < 3; i++){
      System.out.println(i);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

/**
 * Creating a thread by implementing the Runnable interface and overriding run method.
 */
class Two implements Runnable {
  @Override
  public void run() {
    for(int i = 0; i < 5; i++){
      System.out.println(i);
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}

public class StartingThreads {

  public static void main(String[] args){
    One oneA = new One();
    One oneB = new One();
    oneA.start();
    oneB.start();

    Thread t1 = new Thread(new Two());
    Thread t2 = new Thread(new Two());
    t1.start();
    t2.start();

    /**
     * Creating a thread by using anonymous class inside Thread constructor.
     */
    Thread tAnonymous = new Thread(new Runnable() {
      @Override
      public void run() {
        for(int i = 0; i < 7; i++){
          System.out.println(i);
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });

    /**
     * We call the start method instead of the run method. That is because if you call the run method, the
     * code will starting executing the main thread itself. Calling start method, runs the code in its own
     * new thread.
     */
    tAnonymous.start();
  }

}
