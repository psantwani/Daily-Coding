package com.piyush.multithreading;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
  private int amount = 10000;

  private void credit(int amount){
    this.amount += amount;
  }

  private void debit(int amount){
    this.amount -= amount;
  }

  public int getBalance(){
    return amount;
  }

  public static void transfer(Account from, Account to, int amount){
    from.debit(amount);
    to.credit(amount);
  }
}

class Runner2 {

  private Random random = new Random();
  private Account account1 = new Account();
  private Account account2 = new Account();

  /*
  Having 2 locks here (one for account 1 and account 2) is only representational. The code below does
  not connect the 2 locks to the 2 accounts. But when you have many accounts and many threads running,
  you would want to have provision for mapping a lock to an account (like a database would also do if using
  transactional). Only using single lock would be wrong, because that would stop a thread from transferring an
  amount between 2 accounts unrelated to the account on which the lock has been acquired at the moment.
   */
  private Lock lock1 = new ReentrantLock();
  private Lock lock2 = new ReentrantLock();


  public void acquireLocks(Lock lock1,Lock lock2) throws InterruptedException {
    while (true){

      boolean lock1Acquired = false;
      boolean lock2Acquired = false;

      // Tries to acquire locks
      try {
        lock1Acquired = lock1.tryLock();
        lock2Acquired = lock2.tryLock();
      }
      finally {

        // If lock is acquired.
        if(lock1Acquired && lock2Acquired){
          return;
        }

        // If only either lock is acquired, unlock it for some other thread to acquire it.
        if(lock1Acquired){
          lock1.unlock();
        }

        if(lock2Acquired){
          lock2.unlock();
        }

        // Try acquiring lock after 1 msec.
        Thread.sleep(1);
      }
    }
  }

  public void firstThread() throws InterruptedException{
    for(int i = 0; i < 1000; i++){
//      lock1.lock();
//      lock2.lock();
      acquireLocks(lock1, lock2);
      try {
        Account.transfer(account1, account2, random.nextInt(100));
      } finally {
        lock1.unlock();
        lock2.unlock();
      }
    }
  }

  public void secondThread() throws InterruptedException{
    for(int i = 0; i <= 999; i++){
      /*
        For some reason, you would want to acquire lock2 before lock1, unlike in the method above.
        If we execute the following, firstThread would acquire lock on lock1 and second thread on lock2.
        And they both would wait for each other to acquire locks on the other lock, hence leading to deadlock.
        One of dealing with it is to always lock in the same order, else use the acquireLocks method. Read above.
       */
//      lock2.lock();
//      lock1.lock();
      acquireLocks(lock2, lock1);
      try {
        Account.transfer(account2, account1, random.nextInt(100));
      } finally {
        lock1.unlock();
        lock2.unlock();
      }
    }
  }

  public void finished(){
    System.out.println("Account 1 balance : " + account1.getBalance());
    System.out.println("Account 2 balance : " + account2.getBalance());
    System.out.println("Total balance : " + (account1.getBalance() + account2.getBalance()));
  }

}

public class Deadlock {

  public static void main(String ... args){

    Runner2 runner = new Runner2();

    Thread tA = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          runner.firstThread();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    Thread tB = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          runner.secondThread();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    tA.start();
    tB.start();

    try {
      tA.join();
      tB.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    runner.finished();
  }

}
