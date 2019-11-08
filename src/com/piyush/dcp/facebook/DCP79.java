package com.piyush.dcp.facebook;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This problem was asked by Facebook.
 *
 * Given an array of integers, write a function to determine whether the array could
 * become non-decreasing by modifying at most 1 element.
 *
 * For example, given the array [10, 5, 7], you should return true,
 * since we can modify the 10 into a 1 to make the array non-decreasing.
 *
 * Given the array [10, 5, 1], you should return false, since we can't modify any one element to get a non-decreasing array.
 */
public class DCP79 {

  private static boolean solution(int[] input) {
    int N = input.length;
    int[] dp = new int[N];
    dp[0] = 0;

    for(int i = 1; i < N; i++){
      int curr = input[i];
      if(curr < input[i-1]){
        dp[i] = dp[i-1] + 1;
      }
      else {
        dp[i] = dp[i-1];
      }

      if(dp[i] > 1){
        System.out.println("Result: false");
        return false;
      }
    }

    System.out.println("Result: true");
    return true;
  }

  public static void main(String[] args) {
    int[] input = new int[]{10,5,7};
    solution(input);
    System.out.println("------------");

    input = new int[]{10,5,1};
    solution(input);
    System.out.println("------------");

    input = new int[]{1,3,8,281,137};
    solution(input);
    System.out.println("------------");

    input = new int[]{100, 3, 281, 4};
    solution(input);
    System.out.println("------------");
  }

}