package com.piyush.easy;

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
 *    0
 *   / \
 *  3   3
 *     / \
 *    2   2
 *   / \
 *  2   2
 *
 * answer is 6.
 *    0
 *   / \
 *  1   2
 *     / \
 *    3   4
 *   / \
 *  5   6
 *
 *  answer is 4.
 */

public class DCP8 {

  private static int univalSubTrees = 0;

  static class Node {
	Integer val;
	Node left;
	Node right;

	Node(Integer val) {
	  this.val = val;
	}

	Node(Integer val, Node left, Node right) {
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
  private static boolean solution(Node root) {

	if (root == null) {
	  return true;
	}

	int leftVal = root.left == null ? 0 : root.left.val;
	int rightVal = root.right == null ? 0 : root.right.val;

	boolean left = solution(root.left);
	boolean right = solution(root.right);

	if (left && right && leftVal == rightVal) {
	  univalSubTrees++;
	}

	int rootVal = root.val;
	int subTreeValue = root.left == null ? rootVal : root.left.val;

	return left && right && rootVal == subTreeValue;
  }

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

	Node root2 = new Node(0);
	root2.left = new Node(3);
	root2.right = new Node(3);

	root2.right.left = new Node(2);
	root2.right.left.left = new Node(2);
	root2.right.left.right = new Node(2);

	root2.right.right = new Node(2);

	univalSubTrees = 0;
	solution(root2);
	System.out.println("UnivalSubTrees count : " + univalSubTrees);

	Node root3 = new Node(0);
	root3.left = new Node(1);
	root3.right = new Node(2);

	root3.right.left = new Node(3);
	root3.right.left.left = new Node(4);
	root3.right.left.right = new Node(5);

	root3.right.right = new Node(6);

	univalSubTrees = 0;
	solution(root3);
	System.out.println("UnivalSubTrees count : " + univalSubTrees);
  }
}
