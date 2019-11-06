package com.piyush.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given k sorted singly linked lists, write a function to merge all the lists into one sorted singly linked list.
 */

public class DCP78 {

  static class ListNode{
    Integer data;
    ListNode next;

    ListNode(Integer data){
      this.data = data;
    }
  }

  /**
   * Approach-
   * Create a minHeap of size k. Add the first element from each list, and then poll the min value and replace it with
   * the "next" node of the min node. If the next node is null, it implies we have finished traversing that list and
   * now only k-1 nodes will be there in the heap. Keep doing this till the heap is empty.
   */
  private static void merge(List<ListNode> kSortedLists){
    ListNode node = new ListNode(null);
    ListNode head = node;

    PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>((a,b) -> {
      return a.data - b.data;
    });

    queue.addAll(kSortedLists);

    while (!queue.isEmpty()){
      ListNode minNode = queue.peek();
      node.next = new ListNode(minNode.data);
      node = node.next;
      queue.poll();
      if(minNode.next != null) {
        queue.add(minNode.next);
      }
    }

    System.out.println("Printing sorted list");
    StringBuilder output = new StringBuilder();
    head = head.next;
    while (head != null){
      output.append(head.data).append("->");
      head = head.next;
    }

    output.append("null");
    System.out.println(output);
  }

  public static void main(String[] args){
    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(3);
    ListNode l1Head = l1;

    l1 = l1.next;
    l1.next = new ListNode(5);
    l1 = l1.next;
    l1.next = new ListNode(7);
    l1 = l1.next;
    l1.next = null;

    ListNode l2 = new ListNode(2);
    l2.next = new ListNode(4);
    ListNode l2Head = l2;

    l2 = l2.next;
    l2.next = new ListNode(6);
    l2 = l2.next;
    l2.next = new ListNode(8);
    l2 = l2.next;
    l2.next = null;

    ListNode l3 = new ListNode(0);
    l3.next = new ListNode(9);
    ListNode l3Head = l3;

    l3 = l3.next;
    l3.next = new ListNode(10);
    l3 = l3.next;
    l3.next = new ListNode(11);
    l3 = l3.next;
    l3.next = null;

    List<ListNode> kSortedLists = new ArrayList<>();
    kSortedLists.add(l1Head);
    kSortedLists.add(l2Head);
    kSortedLists.add(l3Head);
    merge(kSortedLists);
  }

}
