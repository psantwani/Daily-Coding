package com.piyush.dailycodingproblem.google;

/**
 * This problem was asked by Google.
 *
 * Given the root of a binary tree, return a deepest node. For example, in the following tree, return d.
 *
 *     a
 *    / \
 *   b   c
 *  /
 * d
 */

public class DCP80 {

  static class Node {
    Character val;
    Node left;
    Node right;

    Node(Character val) {
      this.val = val;
    }

    Node(Character val, Node left, Node right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

  static class LeafNodeDepth{

    Node node;
    Integer depth = 0;

    LeafNodeDepth(){}

    LeafNodeDepth(Node node, Integer depth){
      this.node = node;
      this.depth = depth;
    }
  }

  private static LeafNodeDepth solution(Node root) {
    if(root == null){
      return new LeafNodeDepth();
    }

    if(root.left == null&& root.right == null){
      return new LeafNodeDepth(root,1);
    }

    LeafNodeDepth leftDeep = solution(root.left);
    LeafNodeDepth rightDeep = solution(root.right);

    if(leftDeep.depth >= rightDeep.depth){
      leftDeep.depth = leftDeep.depth + 1;
      return leftDeep;
    }
    else {
      rightDeep.depth = rightDeep.depth + 1;
      return rightDeep;
    }
  }

  public static void main(String[] args) {
    Node root = new Node('a');
    root.left = new Node('b');
    root.right = new Node('c');
    root.left.left = new Node('d');

    LeafNodeDepth answer = solution(root);
    System.out.println("Deepest node : " + answer.node.val);

    Node root2 = new Node('a');
    root2.left = new Node('b');
    root2.right = new Node('c');

    root2.right.left = new Node('d');
    root2.right.left.left = new Node('e');
    root2.right.left.right = new Node('f');
    root2.right.left.right.right = new Node('g');
    root2.right.right = new Node('h');

    answer = solution(root2);
    System.out.println("Deepest node : " + answer.node.val);
  }

}
