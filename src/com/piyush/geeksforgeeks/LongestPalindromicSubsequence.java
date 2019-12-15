package com.piyush.geeksforgeeks;

import java.util.Arrays;

public class LongestPalindromicSubsequence {

  static int lps(String input){
    int n = input.length();
    int result = 1;
    int[][] dp = new int[n][n];

    for(int i = 0; i < n; i++){
      dp[i][i] = 1;
    }

    for(int i = 2; i < n; i++){
      int left = 0;
      int right = left + i - 1;
      while (right < n){
        if(input.charAt(left) == input.charAt(right)){
          dp[left][right] = left - right == 1 ? 2 : dp[left + 1][right - 1] + 2;
        } else {
          dp[left][right] = Math.max(dp[left][right - 1], dp[left + 1][right]);
        }
        if(dp[left][right] > result){
          result = dp[left][right];
        }
        left++;
        right++;
      }
    }

    return result;
  }

  public static void main(String args[])
  {
    String seq = "GEEKSFORGEEKS";
    System.out.println("The length of the lps is "+ lps(seq));
  }
}
