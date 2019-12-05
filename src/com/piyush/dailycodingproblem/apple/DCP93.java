package com.piyush.dailycodingproblem.apple;

import java.util.Arrays;
import java.util.Collections;

/**
 * This problem was asked by Apple.
 *
 * Given a tree, find the largest tree/subtree that is a BST.
 *
 * Given a tree, return the size of the largest tree/subtree that is a BST.
 */
public class DCP93 {

  static class Node {
    int val;
    Node left;
    Node right;
    int depth;
    int largestLeft;
    int smallestRight;

    public Node(int val){
      this.val = val;
    }
  }

  static class BinaryTree{

    private Node root;
    private int largestSubtreeSize = 0;
    private Node largestSubtreeRoot;

    public Node largestBst(Node node){

      if(node == null){
        return null;
      }

      if (node.left == null && node.right == null){
        node.depth = 1;
        node.largestLeft = node.val;
        node.smallestRight = node.val;
        return node;
      }

      Node left = largestBst(node.left);
      Node right = largestBst(node.right);

      int leftDepth = left != null ? left.depth : 0;
      int rightDepth = right != null ? right.depth : 0;

      int largestLeft = left != null ? left.largestLeft : 0;
      int smallestRight = right != null ? right.smallestRight : 0;

      if(isBst(node, largestLeft, smallestRight)){
        node.depth = leftDepth + rightDepth + 1;
      }

      if(node.depth > largestSubtreeSize){
        largestSubtreeSize = node.depth;
        largestSubtreeRoot = node;
      }

      return node;
    }

    private boolean isBst(Node root, int largestLeft, int smallestRight){

      if(root == null){
        return true;
      }

      if(root.left == null && root.right == null){
        return true;
      }

      boolean leftBstVariant = false;
      boolean rightBstVariant = false;

      if(root.left == null){
        leftBstVariant = true;
      } else if(root.left.val < root.val && largestLeft < root.val){
        leftBstVariant = true;
      }

      if(root.right == null){
        rightBstVariant = true;
      } else if(root.right.val > root.val && smallestRight > root.val){
        rightBstVariant = true;
      }

      if(leftBstVariant && rightBstVariant){
        Integer a = root.val;
        Integer b = root.left != null ? root.left.largestLeft : a;
        Integer c = root.left != null ? root.left.smallestRight : a;
        Integer d = root.right != null ? root.right.largestLeft : a;
        Integer e = root.right != null ? root.right.smallestRight : a;
        Integer[] arr = new Integer[]{a,b,c,d,e};
        Arrays.sort(arr);
        root.smallestRight = arr[0];
        Arrays.sort(arr, Collections.reverseOrder());
        root.largestLeft = arr[0];
      }


      return leftBstVariant && rightBstVariant;
    }
  }

  public static void main(String ... args){
    BinaryTree tree = new BinaryTree();
    tree.root = new Node(50);
    tree.root.left = new Node(10);
    tree.root.right = new Node(60);
    tree.root.left.left = new Node(5);
    tree.root.left.right = new Node(20);
    tree.root.right.left = new Node(55);
    tree.root.right.left.left = new Node(45);
    tree.root.right.right = new Node(70);
    tree.root.right.right.left = new Node(65);
    tree.root.right.right.right = new Node(80);

    /*
           50
         /    \
        10     60
       / \     / \
      5  20   55  70
             /    / \
            45   65 80

     */

    tree.largestBst(tree.root);
    System.out.println("Size of largest BST is " + tree.largestSubtreeSize + " with root : " + tree.largestSubtreeRoot.val);
  }
}
