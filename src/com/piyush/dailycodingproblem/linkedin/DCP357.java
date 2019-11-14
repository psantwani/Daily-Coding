package com.piyush.dailycodingproblem.linkedin;

/**
 * This problem was asked by LinkedIn.
 *
 * You are given a binary tree in a peculiar string representation. Each node is written in the form (lr),
 * where l corresponds to the left child and r corresponds to the right child.
 *
 * If either l or r is null, it will be represented as a zero. Otherwise, it will be represented by a new (lr) pair.
 *
 * Here are a few examples:
 *
 * A root node with no children: (00)
 * A root node with two children: ((00)(00))
 * An unbalanced tree with three consecutive left children: ((((00)0)0)0)
 * Given this representation, determine the depth of the tree.
 */
public class DCP357 {

  static class Tuple{
    int depth;
    int pos;

    Tuple(int depth, int pos){
      this.depth = depth;
      this.pos = pos;
    }
  }

  /**
   * Approach :
   * Recurse over the input string. For every "(" we need a left and right child node,
   * hence we call the recursive method twice, once for left and once for right.
   * We recurse, till we encounter our first null node on which we return a tuple of depth = 0 && string position.
   * We need this string position because we want to start scanning for the right node from this string position + 1 index.
   * Do the same for the right child.
   * Once both the tuples are calculated, we take the max of the depths from the left and right tuple and add one to it.
   * We return a new tuple with this max + 1 depth and pos = pos of the right tuple + 1 (this way we cover the ")" char of
   * this node.)
   */
  public static int solution(String input){
    int N = input.length();

    if(N < 4){
      return 0;
    }

    if(N == 4){
      return 1;
    }

    Tuple result = recursive(input,0 , 0);

    System.out.println("Depth of the tree : " + result.depth);
    return result.depth;
  }

  private static Tuple recursive(String input, int pos, int depth){
    char curr = input.charAt(pos);
    if(curr == '0'){
      return new Tuple(0, pos);
    }

    Tuple left = recursive(input, pos + 1, depth);
    Tuple right = recursive(input,left.pos + 1, depth);

    depth = Math.max(left.depth, right.depth) + 1;
    return new Tuple(depth, right.pos + 1);
  }


  public static void main(String[] args){
    String input = "(00)";
    solution(input);

    input = "((00)(00))";
    solution(input);

    input = "((((00)0)0)0)";
    solution(input);

    input = "((((00)(00))0)0)";
    solution(input);
  }

}