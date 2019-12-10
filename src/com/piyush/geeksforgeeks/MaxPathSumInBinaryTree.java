package com.piyush.geeksforgeeks;

public class MaxPathSumInBinaryTree {

  static class Node{
    int val;
    Node left;
    Node right;

    public Node(int val){
      this.val = val;
    }
  }

  static class BinaryTree{
    Node root;
    int maxSum = 0;

    public BinaryTree(){}

    public int findMaxSum(Node node){
      if(node == null){
        return 0;
      }

      if(node.left == null && node.right == null){
        return node.val;
      }
      int left = findMaxSum(node.left);
      int right = findMaxSum(node.right);

      int nodeOnly = node.val;
      int nodeAndLeft = node.val + left;
      int nodeAndRight = node.val + right;
      int leftAndNodeAndRight = left + node.val + right;

      if(maxSum < leftAndNodeAndRight){
        maxSum = leftAndNodeAndRight;
      }

      int largest = Math.max(Math.max(nodeOnly, nodeAndLeft), Math.max(nodeOnly, nodeAndRight));
      return largest;
    }

  }

  public static void main(String args[]) {
    BinaryTree tree = new BinaryTree();
    tree.root = new Node(10);
    tree.root.left = new Node(2);
    tree.root.right = new Node(10);
    tree.root.left.left = new Node(20);
    tree.root.left.right = new Node(1);
    tree.root.right.right = new Node(-25);
    tree.root.right.right.left = new Node(3);
    tree.root.right.right.right = new Node(4);
    tree.findMaxSum(tree.root);
    System.out.println("maximum path sum is : " + tree.maxSum);

  }

}
