package com.piyush.multithreading;

public class Synchronized {

  private int count = 0;

  /**
   * Simply doing a count++ in threads t1 and t2 will give wrong final count.
   * Reason : count++ although seems like an atomic operation actually involves three steps,
   * count = count + 1.
   * 1. Reading current value of count.
   * 2. Adding one.
   * 3. Assigning updated value.
   * Hence, when 2 threads are doing that operation, it is possible that both might see the same current value,
   * lets say 100. After t1 and t2 execute a loop iteration, expected count would be 102, but it would actually by 101
   * because they both read 100 as the starting count.
   * Hence we create a method increment and make it synchronized.
   * Every object in java has an intrinsic lock or monitor lock, also called Mutex.
   * When you are calling a method that is synchronized, you need first acquire the intrinsic lock, before you can
   * call the method. And the good thing is only one thread can acquire the lock at a time. As a result of which,
   * we get the final count as 200000. Volatile is not what is needed here, because the problem here is not of
   * the visibility of the variable or optimizing it, but sharing it across multiple threads.
   */
  public synchronized void increment(){
    count++;
  }

  public static void main(String[] args){
    Synchronized s = new Synchronized();
    s.doWork();
  }

  public void doWork(){
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for(int i = 0 ; i < 10000; i++){
          increment();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        for(int i = 0 ; i < 10000; i++){
          increment();
        }
      }
    });

    t1.start();
    t2.start();

    /*
      What is t1.join(). It makes the calling thread wait till t1 has finished executing.
      This needs to be done else printing count on the next line will show a very small value, since the
      2 threads wouldn't be done executing completely yet.
     */
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Count : " + count);
  }

}
