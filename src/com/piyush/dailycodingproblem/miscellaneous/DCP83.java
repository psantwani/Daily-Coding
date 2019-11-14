package com.piyush.dailycodingproblem.miscellaneous;

/**
 * Invert a binary tree.
 *
 * For example, given the following tree:
 *
 *     a
 *    / \
 *   b   c
 *  / \  /
 * d   e f
 * should become:
 *
 *   a
 *  / \
 *  c  b
 *  \  / \
 *   f e  d
 */
public class DCP83 {

  static class Node {

    char data;
    Node left, right;

    Node(char item) {
      data = item;
      left = right = null;
    }
  }

  public static void solution(Node root){
    postOrder(root);
  }

  private static Node postOrder(Node root){
    if(root == null){
      return null;
    }
    if(root.left == null && root.right == null){
      return root;
    }

    Node left = postOrder(root.left);
    Node right = postOrder(root.right);

    root.left = right;
    root.right = left;

    return root;
  }

  public static void main(String[] args){
    Node root = new Node('a');
    root.left = new Node('b');
    root.right = new Node('c');

    root.left.left = new Node('d');
    root.left.right = new Node('e');

    root.right.left = new Node('f');
    solution(root);
  }

}
