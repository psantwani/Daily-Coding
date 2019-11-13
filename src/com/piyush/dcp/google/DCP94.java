package com.piyush.dcp.google;

/**
 * This problem was asked by Google.
 *
 * Given a binary tree of integers, find the maximum path sum between two nodes.
 * The path must go through at least one node, and does not need to go through the root.
 */
public class DCP94 {

  static Integer maxSum = Integer.MIN_VALUE;

  static class Node {

    int data;
    Node left, right;

    Node(int item) {
      data = item;
      left = right = null;
    }
  }

  /**
   * Approach
   * Start with postorder traversal. When the root node receives, the left sum and right sum, there could be 2 possible
   * paths:
   * 1. Take both left and right as a part of the path : left + right + root.data. Update maxSum if greater.
   * 2. Take max of left and right and add root.data and go upwards to be a part of another path. Update maxSum if greater.
   * 3. Return point no. 2 value in that recursive call.
   */
  public static int solution(Node root){
    postorder(root);
    return maxSum;
  }

  private static int postorder(Node root){
    if(root == null){
      return 0;
    }

    if(root.left == null && root.right == null){
      return root.data;
    }

    int left = postorder(root.left);
    maxSum = Math.max(left, maxSum);

    int right = postorder(root.right);
    maxSum = Math.max(right, maxSum);

    // If I take both the left and right subtree.
    if(root.left != null && root.right != null){
      maxSum = Math.max((left + right + root.data), maxSum);
    }
    else if(root.left != null || root.right != null){
      // If I take the better subtree and go up.
      maxSum = Math.max((Math.max(left, right) + root.data), maxSum);
    }

    return (Math.max(left, right) + root.data);
  }

  public static void main(String[] args){
    Node root = new Node(-15);
    root.left = new Node(5);
    root.right = new Node(6);
    root.left.left = new Node(-8);
    root.left.right = new Node(1);
    root.left.left.left = new Node(2);
    root.left.left.right = new Node(6);
    root.right.left = new Node(3);
    root.right.right = new Node(9);
    root.right.right.right = new Node(0);
    root.right.right.right.left = new Node(4);
    root.right.right.right.right = new Node(-1);
    root.right.right.right.right.left = new Node(10);
    System.out.println("Max pathSum of the given binary tree is " + solution(root));
  }
}
