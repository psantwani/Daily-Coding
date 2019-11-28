package com.piyush.multithreading;

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

    tAnonymous.start();
  }

}
