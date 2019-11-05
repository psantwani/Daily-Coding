package com.piyush.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Daily Coding Problem: Problem #1 [Easy]
 * <p>
 * This problem was recently asked by Google.
 * <p>
 * Given a list of numbers and a number k, return whether any two numbers from the list add up to k.
 * <p>
 * For example, given [10, 15, 3, 7] and k of 17, return true since 10 + 7 is 17.
 * <p>
 * Bonus: Can you do this in one pass?
 */

public class DCP1 {

  private static boolean solution(Integer[] input, Integer k) {
    Map<Integer, Integer> map = new HashMap<>();
    for (Integer i = 0; i < input.length; i++) {
      Integer num = input[i];
      if (map.containsKey(num)) {
        System.out.println("True.");
        return true;
      }
      map.put(k - num, i);
    }

    System.out.println("False.");
    return false;
  }

  public static void main(String[] args) {
    Integer[] input = new Integer[] {10, 15, 3, 7};
    Integer k = 17;
    solution(input, k);

    input = new Integer[] {10, 15, 3, 7};
    k = 19;
    solution(input, k);
  }


}
