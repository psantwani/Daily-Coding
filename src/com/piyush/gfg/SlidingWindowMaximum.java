package com.piyush.gfg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given an array and an integer K, find the maximum for each and every contiguous subarray of size k.
 * Input: arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}, K = 3
 * Output: 3 3 4 5 5 5 6
 *
 *
 *
 * Input: arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}, K = 4
 * Output: 10 10 10 15 15 90 90
 */

public class SlidingWindowMaximum {

  private static List<Integer> solution(Integer[] input, Integer k) {
    List<Integer> result = new ArrayList<>();

    PriorityQueue<Integer> queue = new PriorityQueue<>((a,b) -> {
      return b - a;
    });

    int index = 0;
    while (index < k){
      queue.add(input[index++]);
    }

    while (index < input.length){
      result.add(queue.peek());
      queue.remove(input[index - k]);
      queue.add(input[index++]);
    }

    result.add(queue.peek());

    System.out.println(Arrays.toString(result.toArray()));
    return  result;
  }

  public static void main(String[] args) {
    Integer[] input = new Integer[]{1, 2, 3, 1, 4, 5, 2, 3, 6};
    int k = 3;
    solution(input, k);

    input = new Integer[]{8, 5, 10, 7, 9, 4, 15, 12, 90, 13};
    k = 4;
    solution(input, k);
  }

}
