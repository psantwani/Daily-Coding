package com.piyush.geeksforgeeks;

public class MergeSortLinkedList {

  static class Node{
    int val;
    Node next;

    public Node(int val){
      this.val = val;
    }
  }

  static class LinkedList{
    Node head;

    public void push(int val){
      if(head == null){
        head = new Node(val);
      } else{
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
      }
    }

    public Node mergeSort(Node head){
      if(head == null || head.next == null){
        return head;
      }

      Node middleNode = getMiddleOfList(head);
      Node nextOfMiddle = middleNode.next;

      middleNode.next = null;

      Node left = mergeSort(head);

      Node right = mergeSort(nextOfMiddle);

      Node sortedList = sortedMerge(left, right);

      return sortedList;
    }

    private Node sortedMerge(Node left, Node right){
      Node result = null;

      if(left == null){
        return right;
      }

      if(right == null){
        return left;
      }

      if(left.val < right.val){
        result = left;
        result.next = sortedMerge(left.next, right);

      } else {
        result = right;
        result.next = sortedMerge(left, right.next);
      }

      return result;
    }

    private Node getMiddleOfList(Node head){
      if(head == null){
        return head;
      }

      Node slow = head;
      Node fast = head;

      while (fast.next != null && fast.next.next != null){
        slow = slow.next;
        fast = fast.next.next;
      }

      return slow;
    }

    public void printList(Node head){
      while (head != null){
        System.out.print(head.val + " ");
        head = head.next;
      }
    }
  }

  public static void main(String[] args)
  {

    LinkedList li = new LinkedList();
    /*
     * Let us create a unsorted linked list to test the functions
     * created. The list shall be a: 2->3->20->5->10->15
     */
    li.push(15);
    li.push(10);
    li.push(5);
    li.push(20);
    li.push(3);
    li.push(2);

    // Apply merge Sort
    li.head = li.mergeSort(li.head);
    System.out.print("Sorted Linked List is: ");
    li.printList(li.head);
  }

}
