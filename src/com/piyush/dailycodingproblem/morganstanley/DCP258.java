package com.piyush.dailycodingproblem.morganstanley;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * This problem was asked by Morgan Stanley.
 *
 * In Ancient Greece, it was common to write text with the first line going left to right,
 * the second line going right to left, and continuing to go back and forth. This style was called "boustrophedon".
 *
 * Given a binary tree, write an algorithm to print the nodes in boustrophedon order.
 *
 * For example, given the following tree:
 *
 *        1
 *     /     \
 *   2         3
 *  / \       / \
 * 4   5     6   7
 * You should return [1, 3, 2, 4, 5, 6, 7].
 */
public class DCP258 {

  static class Node {

    int data;
    Node left, right;

    Node(int item) {
      data = item;
      left = right = null;
    }
  }

  public static void solution(Node root){
    List<Integer> result = new ArrayList<>();
    Queue<Node> queue = new LinkedList();
    queue.add(root);
    queue.add(null);

    char direction = 'L';

    while (!queue.isEmpty()){

      Node curr = queue.poll();

      if(curr == null){

        if(queue.size() == 0){
          break;
        }

        queue.add(null);
        direction = direction == 'L' ? 'R' : 'L';
        continue;
      }

      result.add(curr.data);

      if(direction == 'L'){
        if(curr.right != null){
          queue.add(curr.right);
        }
        if(curr.left != null){
          queue.add(curr.left);
        }
      } else if(direction == 'R'){
        if(curr.left != null){
          queue.add(curr.left);
        }
        if(curr.right != null){
          queue.add(curr.right);
        }
      }
    }

    System.out.println(Arrays.toString(result.toArray()));
  }

  public static void main(String[] args){
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);

    root.left.left = new Node(4);
    root.left.right = new Node(5);

    root.right.left = new Node(6);
    root.right.right = new Node(7);

    solution(root);
  }
}
