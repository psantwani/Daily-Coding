package com.piyush.geeksforgeeks;

public class InsertInBalancedBT {

  public static void main(String[] args) {
	AVLTree tree = new AVLTree();

	/* Constructing tree given in the above figure */
	tree.root = tree.insert(tree.root, 10);
	tree.root = tree.insert(tree.root, 20);
	tree.root = tree.insert(tree.root, 30);
	tree.root = tree.insert(tree.root, 40);
	tree.root = tree.insert(tree.root, 50);
	tree.root = tree.insert(tree.root, 25);

        /* The constructed AVL Tree would be
             30
            /  \
          20   40
         /  \     \
        10  25    50
        */
	System.out.println("Preorder traversal" +
		" of constructed tree is : ");
	tree.preOrder(tree.root);
  }

  static class Node {
	int val;
	int height;
	Node left;
	Node right;

	public Node(int val) {
	  this.val = val;
	  this.height = 1;
	}
  }

  static class AVLTree {
	Node root;

	private static Node rightRotate(Node root) {
	  Node left = root.left;
	  Node maxOnLeft = left.right;

	  left.right = root;
	  root.left = maxOnLeft;

	  left.height = height(left);
	  root.height = height(root);

	  return left; // new root;
	}

	private static Node leftRotate(Node root) {
		Node right = root.right;
		Node smallestOnRight = right.left;

		right.left = root;
		root.right = smallestOnRight;

		right.height = height(right);
		root.height = height(root);

		return right;
	}

	private static int height(Node node) {
	  if (node == null) {
		return 0;
	  }

	  return 1 + Math.max(height(node.left), height(node.right));
	}

	private static int getBalance(Node node) {
	  if (node == null) {
		return 0;
	  }

	  int left = node.left != null ? node.left.height : 0;
	  int right = node.right != null ? node.right.height : 0;

	  return left - right;
	}

	Node insert(Node node, int val) {
	  if (node == null) {
		node = new Node(val);
		return node;
	  }

	  // Regular BST insertion.
	  if (val < node.val) {
		node.left = insert(node.left, val);
	  } else if (val > node.val) {
		node.right = insert(node.right, val);
	  } else {
		return node;
	  }

	  // Get height and balance factor.
	  node.height = height(node);
	  int balance = getBalance(node);

	  // left left case
	  if (balance > 1 && val < node.left.val) {
		return rightRotate(node);
	  }
	  // right right case
	  else if (balance < -1 && val > node.right.val) {
		return leftRotate(node);
	  }
	  // left right case
	  else if (balance > 1 && val > node.left.val) {
		node.left = leftRotate(node.left);
		return rightRotate(node);
	  }
	  // right left case
	  else if (balance < -1 && val < node.right.val) {
		node.right = rightRotate(node.right);
		return leftRotate(node);
	  }

	  return node;
	}

	void preOrder(Node node) {
	  if (node != null) {
		System.out.print(node.val + " ");
		preOrder(node.left);
		preOrder(node.right);
	  }
	}
  }

}
