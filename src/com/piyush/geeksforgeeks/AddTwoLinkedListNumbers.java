package com.piyush.geeksforgeeks;

public class AddTwoLinkedListNumbers {

  static class Node{
    int val;
    Node next;

    public Node(int val){
      this.val = val;
    }
  }

  static class LinkedListATN{
    Node head1;
    Node head2;
    Node result;
    int carry = 0;

    public void push(int val, int list){
      Node newNode = new Node(val);
      if (list == 1)
      {
        newNode.next = head1;
        head1 = newNode;
      }
      else if (list == 2)
      {
        newNode.next = head2;
        head2 = newNode;
      }
      else
      {
        newNode.next = result;
        result = newNode;
      }
    }

    public Node reverse(Node head){

      Node prev = null;
      Node curr = head;
      Node next = null;

      while (curr != null) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }

      return prev;
    }

    public Node addLists(int maxLength){
      Node ptr1 = reverse(head1);
      Node ptr2 = reverse(head2);
      int resultLength = 0;
      Node firstNode = null;

      while (resultLength < maxLength) {
        int ptr1Val = ptr1 != null ? ptr1.val : 0;
        int ptr2Val = ptr2 != null ? ptr2.val : 0;

        int sum = ptr1Val + ptr2Val + carry;
        carry = sum / 10;
        sum %= 10;

        Node newNode = new Node(sum);
        if (result == null) {
          result = newNode;
          firstNode = result;
        } else {
          result.next = newNode;
          result = newNode;
        }

        if (ptr1 != null) {
          ptr1 = ptr1.next;
        }

        if (ptr2 != null) {
          ptr2 = ptr2.next;
        }

        resultLength++;
      }

      if(carry > 0){
        Node newNode = new Node(carry);
        result.next = newNode;
        result = newNode;
      }

      result = reverse(firstNode);
      return result;
    }

    public void printList(Node head){
      while (head != null){
        System.out.print(head.val + " ");
        head = head.next;
      }
    }

  }

  public static void main(String ... args){
    LinkedListATN list = new LinkedListATN();
    list.head1 = null;
    list.head2 = null;
    list.result = null;
    list.carry = 0;
    int arr1[] = { 9, 9, 9 };
    int arr2[] = { 1, 8 };

    // Create first list as 9->9->9
    for (int i = arr1.length - 1; i >= 0; --i)
      list.push(arr1[i], 1);

    // Create second list as 1->8
    for (int i = arr2.length - 1; i >= 0; --i)
      list.push(arr2[i], 2);

    list.addLists(Math.max(arr1.length, arr2.length));

    list.printList(list.result);
  }

}
