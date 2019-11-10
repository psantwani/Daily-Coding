package com.piyush.dcp.yext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * This problem was asked by Yext.
 *
 * Two nodes in a binary tree can be called cousins if they are on the same level of the tree but have different parents. For example, in the following diagram 4 and 6 are cousins.
 *
 *     1
 *    / \
 *   2   3
 *  / \   \
 * 4   5   6
 * Given a binary tree and a particular node, find all cousins of that node.
 */

public class DCP284 {

  static class Node {
    Integer val;
    Node left;
    Node right;

    Node(Integer val) {
      this.val = val;
    }

    @Override
    public String toString(){
      return val.toString();
    }
  }

  public static List<Node[]> solution(Node root){
    List<Node[]> result = new ArrayList<>();
    Queue<Node> queue = new LinkedList<>();
    Map<Integer,List<List<Node>>> levelsAndNodes = new HashMap();
    queue.add(root);
    queue.add(null);

    int level = 1;
    while (!queue.isEmpty()){
      Node curr = queue.peek();
      if(curr == null){
        level++;
        queue.poll();
        if(queue.size() != 0){
          queue.add(null);
        }
        continue;
      }
      Node left = curr.left;
      Node right = curr.right;

      List<List<Node>> nodesAtLevel = levelsAndNodes.getOrDefault(level, new ArrayList<>());
      List<Node> childrenOfParent = new ArrayList<>();

      if(left != null){
        queue.add(left);
        childrenOfParent.add(left);
      }

      if(right != null){
        queue.add(right);
        childrenOfParent.add(right);
      }

      if(childrenOfParent.size() != 0){
        nodesAtLevel.add(childrenOfParent);
      }

      if(nodesAtLevel.size() != 0){
        levelsAndNodes.put(level, nodesAtLevel);
      }

      queue.poll();
    }


    for(Map.Entry entry : levelsAndNodes.entrySet()){
        Integer key = (Integer) entry.getKey();
        if(key >=2){
          List<List<Node>> values = (List<List<Node>>) entry.getValue();
          List<Node[]> cousins = getCousins(values);
          result.addAll(cousins);
        }
    }

    for(Node[] cousins : result){
      System.out.println(Arrays.toString(cousins));
    }

    return result;
  }

  static List<Node[]> getCousins(List<List<Node>> nodes){
    List<Node[]> cousins = new ArrayList<>();

    for(int i = 0; i < nodes.size(); i++){
      for(int j = i+1; j < nodes.size(); j++){
        List<Node> childrenOfI = nodes.get(i);
        List<Node> childrenOfJ = nodes.get(j);
        for(int m = 0; m < childrenOfI.size(); m++){
          for(int n = 0; n < childrenOfJ.size(); n++){
            cousins.add(new Node[]{childrenOfI.get(m), childrenOfJ.get(n)});
          }
        }
      }
    }

    return cousins;
  }

  public static void main(String[] args){
    Node root = new Node(1);
    root.left = new Node(2);
    root.right = new Node(3);

    root.left.left = new Node(4);
    root.left.right = new Node(5);

    root.right.right = new Node(6);

    solution(root);

    System.out.println("--------------");

    Node root2 = new Node(1);
    root2.left = new Node(2);
    root2.right = new Node(3);

    root2.left.left = new Node(4);
    root2.left.right = new Node(5);
    root2.left.left.left = new Node(7);
    root2.left.right.right = new Node(8);
    root2.left.left.left.left = new Node(10);
    root2.left.left.left.right = new Node(11);

    root2.right.right = new Node(6);
    root2.right.right.right = new Node(9);
    root2.right.right.right.left = new Node(12);
    root2.right.right.right.left.right = new Node(13);

    solution(root2);
  }

}
