package com.piyush.easy;

import com.piyush.medium.DCP3;

/**
 * This problem was asked by Google.
 *
 * A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.
 *
 * Given the root to a binary tree, count the number of unival subtrees.
 *
 * For example, the following tree has 5 unival subtrees:
 *
 *    0
 *   / \
 *  1   0
 *     / \
 *    1   0
 *   / \
 *  1   1
 *
 */

public class DCP8 {

  private static int univalSubTrees = 0;

  static class Node{
	Integer val;
	Node left;
	Node right;

	Node(Integer val){
	  this.val = val;
	}

	Node(Integer val, Node left, Node right){
	  this.val = val;
	  this.left = left;
	  this.right = right;
	}
  }

  /**
   * Approach:
   * Every leaf node is going to be a univalSubtree.
   * Other than the leaf node, every node where the left and right node are themselves roots of univalSubtrees and are
   * equal in value, also makes the node a parent of a univalSubtree.
   */
  public static void main(String[] args) {
	Node root = new Node(0);
	root.left = new Node(1);
	root.right = new Node(0);

	root.right.left = new Node(1);
	root.right.left.left = new Node(1);
	root.right.left.right = new Node(1);

	root.right.right = new Node(0);

	univalSubTrees = 0;
	solution(root);
	System.out.println("UnivalSubTrees count : " + univalSubTrees);
  }

  private static boolean solution(Node root){

    if(root == null){
      return true;
	}

	int rootVal = root.val;
    int leftChildVal = root.left == null ? rootVal : root.left.val;

	boolean left = solution(root.left);
    boolean right = solution(root.right);

    if(left && right && rootVal == leftChildVal){
      univalSubTrees++;
	}

	return left && right && rootVal == leftChildVal;
  }
}
