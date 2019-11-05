package com.piyush.medium;

/**
 * Daily Coding Problem: Problem #3 [Medium]
 *
 * This problem was asked by Google.
 *
 * Given the root to a binary tree, implement serialize(root), which serializes the tree into a string, and deserialize(s), which deserializes the string back into the tree.
 *
 * For example, given the following Node class
 *
 * class Node:
 *     def __init__(self, val, left=None, right=None):
 *         self.val = val
 *         self.left = left
 *         self.right = right
 * The following test should pass:
 *
 * node = Node('root', Node('left', Node('left.left')), Node('right'))
 * assert deserialize(serialize(node)).left.left.val == 'left.left'
 *
 * Input:
 *        20
 *        /
 *       8
 *      / \
 *     4  12
 *       /  \
 *      10  14
 *
 * Output: 20 8 4 -1 -1 12 10 -1 -1 14 -1 -1 -1
 */

public class DCP3 {

  private static Integer pos = 0;

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

	@Override
	public String toString(){
      return this.val + " ";
	}
  }

  private static String serialize(Node root){

    if(root == null){
      return "-1 ";
	}

	String s = root.toString();

	String left = serialize(root.left);
    s += left.toString();

    String right = serialize(root.right);
	s += right.toString();

    return s;
  }

  private static Node deserialize(String s){

    if(s == null || s.length() == 0){
      return null;
	}

    String[] values = s.split(" ");
    if(values.length == 1){
	  return new Node(Integer.valueOf(values[0]), null, null);
	}

	return deserialize(values);
  }

  private static Node deserialize(String[] input){

    if(pos >= input.length){
      return null;
	}

    int val = Integer.parseInt(input[pos]);

    if(val == -1){
      return null;
	}

	Node node = new Node(val);

    pos = pos + 1;
    node.left = deserialize(input);

    pos = pos + 1;
	node.right = deserialize(input);

    return node;
  }

  public static void main(String[] args) {
    Node root = new Node(20);
    root.left = new Node(8);

    root.left.left = new Node(4);
    root.left.right = new Node(12);

    root.left.right.left = new Node(10);
    root.left.right.right = new Node(14);

    String s1 = serialize(root);
    System.out.println("First time serialization : " + s1);

    root = deserialize(s1);

    String s2 = serialize(root);
    System.out.println("Serialization after deserialization : " + s2);

    System.out.println("Matches : " + s1.equals(s2));
  }

}
