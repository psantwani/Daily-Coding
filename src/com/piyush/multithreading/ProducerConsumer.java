package com.piyush.multithreading;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumer {

  // Thread-safe FIFO queue. You can access it from multiple threads.
  private BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

  public static void main(String ... args){

  }

  // One or more producer threads will add to queue.
  private void producer() throws InterruptedException {
    Random random = new Random();

    while (true){
      // No need to synchronize. Its already taken care by the thread safety in BlockingQueue.
      queue.put(random.nextInt(100));
    }
  }

  // One or more consumer threads will take integers from queue.
  private void consumer() throws InterruptedException {
    Random random = new Random();
    while (true){
      Thread.sleep(100); // Will take integers after every 100 millisecond, i.e. 10 loops a second.

      /* Basically, we are take from queue, when random number == 0, whose probability is 1/10, i.e. once in 1 second
      since we are already waiting for 100 msec in each while loop iteration.
      */
      if(random.nextInt(10) == 0){

        // No need to synchronize. Its already taken care by the thread safety in BlockingQueue..
        Integer value = queue.take();

        System.out.println("Taken value : " + value + "; Queue size is : " + queue.size());
      }
    }
  }

}
