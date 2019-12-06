package com.piyush.oodesign.CircularQueue;

/**
 * Implement queue by circulant array. You need to support the following methods:
 *
 * CircularQueue(n): initialize a circular array with size n to store elements
 * boolean isFull(): return true if the array is full
 * boolean isEmpty(): return true if there is no element in the array
 * void enqueue(element): add an element to the queue
 * int dequeue(): pop an element from the queue
 * Notice
 *
 * It’s guaranteed in the test cases we won’t call enqueue
 * if it’s full and we also won’t call dequeue if it’s empty.
 * So it’s ok to raise exception in scenarios described above.
 */
public class CircularQueueMain {
  public static void main(String ... args){
    CircularQueue queue = new CircularQueue(5);
    System.out.println(queue.isFull());
    System.out.println(queue.isEmpty());
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    System.out.println(queue.dequeue());
    queue.dequeue();
  }

}

class CircularQueue{

  private Integer[] circularArr;
  private int capacity;
  private int size;
  private int front; // dequeue from here.
  private int rear; // enqueue here.

  public CircularQueue(int n){
    this.capacity = n;
    this.circularArr = new Integer[n];
    this.size = 0;
    this.front = -1;
    this.rear = -1;
  }

  public boolean isEmpty(){
    return size == 0;
  }

  public boolean isFull(){
    return size == capacity;
  }

  public void enqueue(int element){
    if(this.isFull()){
      throw new IllegalStateException("Queue is full");
    }

    rear = (rear + 1) % capacity;
    circularArr[rear] = element;
    size++;

    if(front == -1){
      front = rear;
    }
  }

  public int dequeue(){
    if(this.isEmpty()){
      throw new IllegalStateException("Queue is empty");
    }

    int element = circularArr[front];
    circularArr[front] = null;
    front = (front + 1) % capacity;
    size--;

    return element;
  }
}
