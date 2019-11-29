package com.piyush.multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* The worker object has only 1 intrinsic lock associated with it. So if we declare addToList1 and addToList2 as
synchronized, when thread t1 tries to acquire lock to execute  addToList1, thread 2 can't do anything. It can't
even independently execute addToList2, even though the 2 methods are operating on different lists. So this ends
 up taking 4 seconds to complete execution instead of 2.Hence, instead of make the methods synchronized,
 we write synchronized blocks with 2 lock objects.
 */
class Worker{

  private Random random = new Random();
  List<Integer> list1 = new ArrayList<>();
  List<Integer> list2 = new ArrayList<>();

  Object lock1 = new Object();
  Object lock2 = new Object();

  private void addToList1(){
    synchronized (lock1){
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      list1.add(random.nextInt(100));
    }
  }

  private void addToList2(){
    synchronized (lock2){
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        // Do nothing.
      }
      list2.add(random.nextInt(100));
    }
  }

  public void process(){
    for(int i = 0; i < 1000; i++){
      addToList1();
      addToList2();
    }
  }
}

public class MultipleLocks {

  public static void main(String ... args){
    Worker worker = new Worker();

    System.out.println("Starting...");
    long startTime = System.currentTimeMillis();

    /* Running the following commented line this will call the process method from the
    main thread and it will always complete execution in 2 seconds with each list
    containing 1000 elements each. 1000*1 + 1000*1 = 2secs.
     */
    // worker.process();

    /* But let's try to spawn multiple threads that will call the process method independently.
     */
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        worker.process();
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        worker.process();
      }
    });

    /*
     ArrayList is not thread-safe. So when two threads are simultaneously trying to add an element in
     say list1, its possible that both threads started with the same initial list size. Thread 2 managed to
     add the element first, list1 size has changed now before thread 1 could add a new element. That might
     lead to ArrayIndexOutOfBounds exception.

     This exception can be avoid by using -
      List<Integer> list1 = Collections.synchronizedList(new ArrayList<>());
      List<Integer> list2 = Collections.synchronizedList(new ArrayList<>());

      No need to use synchronized blocks if we do this.

     */
    t1.start();
    t2.start();

    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    long endTime = System.currentTimeMillis();
    System.out.println("Time taken : " + (endTime - startTime));
    System.out.println("List 1 size :" + worker.list1.size() + ", List 2 size : " + worker.list2.size());
  }

}
