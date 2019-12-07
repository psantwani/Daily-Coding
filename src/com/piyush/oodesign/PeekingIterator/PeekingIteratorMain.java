package com.piyush.oodesign.PeekingIterator;

import java.util.Iterator;

/**
 * Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation — it essentially peek() at the element that will be returned by the next call to next().
 *
 * Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 *
 * Call next() gets you 1, the first element in the list.
 *
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 *
 * You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.
 *
 * Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */
public class PeekingIteratorMain {

  public static void main(String ... args){
    PeekingIterator<Integer> peekingIterator = new PeekingIterator<>(new Integer[]{1,2,3});
    System.out.println(peekingIterator.next());
    System.out.println(peekingIterator.peek());
    System.out.println(peekingIterator.next());
    System.out.println(peekingIterator.next());
    System.out.println(peekingIterator.hasNext());
  }

}

class PeekingIterator<T> implements Iterator<T>{

  private T[] iterable;
  private int index;
  private int size;

  public PeekingIterator(T[] iterable){
    this.iterable = iterable;
    this.index = 0;
    this.size = iterable.length;
  }

  @Override
  public boolean hasNext() {
    return index < size;
  }

  @Override
  public T next() {
    return iterable[index++];
  }

  public T peek(){
    return iterable[index];
  }
}
