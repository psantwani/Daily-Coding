package com.piyush.geeksforgeeks;

public class TreeBoundary {

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

    public BinaryTree(){}

    public void printBoundary(Node root){
      System.out.println(root.val);
      printBoundaryLeft(root.left);
      printLeaves(root.left);
	  printLeaves(root.right);
      printBoundaryRight(root.right);
	}

	public void printBoundaryLeft(Node node){
      if(node.left == null && node.right == null){
        return;
	  }
      System.out.println(node.val);
      if(node.left == null){
        printBoundaryLeft(node.right);
	  } else {
		printBoundaryLeft(node.left);
	  }
	}

	public void printBoundaryRight(Node node){

      if(node == null){
        return;
	  }

	  if(node.right == null){
	    printBoundaryRight(node.left);
	  } else {
		printBoundaryRight(node.right);
	  }

	  if(node.left == null && node.right == null){
		return;
	  }

	  System.out.println(node.val);
	  return;
	}

	public void printLeaves(Node node){
		if(node == null){
		  return;
		}

		if(node.left == null && node.right == null){
		  System.out.println(node.val);
		  return;
		}

		printLeaves(node.left);
		printLeaves(node.right);
	}

  }
  public static void main(String args[])
  {
	BinaryTree tree = new BinaryTree();
	tree.root = new Node(20);
	tree.root.left = new Node(8);
	tree.root.left.left = new Node(4);
	tree.root.left.right = new Node(12);
	tree.root.left.right.left = new Node(10);
	tree.root.left.right.right = new Node(14);
	tree.root.right = new Node(22);
	tree.root.right.right = new Node(25);
	tree.printBoundary(tree.root);
  }
}
