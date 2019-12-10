package com.piyush.geeksforgeeks;

import java.util.Random;

public class RandomNodeInLinkedList {

  static class Node{
    int val;
    Node next;

    public Node(int val){
      this.val = val;
    }
  }

  static class LinkedList{
    Node head;
    Random random = new Random();

    public void printRandom(){
      int i = 0;
      Node temp = head;
      Node selected = null;
      while (temp != null){
        if(random.nextInt(i+1) == i){
          selected = temp;
        }

        temp = temp.next;
        i++;
      }

      System.out.println(selected.val);
    }
  }

  public static void main(String ... args){
    LinkedList list = new LinkedList();
    list.head = new Node(5);
    list.head.next = new Node(20);
    list.head.next.next = new Node(4);
    list.head.next.next.next = new Node(3);
    list.head.next.next.next.next = new Node(30);

    list.printRandom();
  }

}
