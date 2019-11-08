package com.piyush.dcp.microsoft;

/**
 * This problem was asked by Microsoft.
 *
 * Given an array of numbers, find the length of the longest increasing subsequence in the array.
 * The subsequence does not necessarily have to be contiguous.
 *
 * For example, given the array [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15],
 * the longest increasing subsequence has length 6: it is 0, 2, 6, 9, 11, 15.
 */

public class DCP75 {
  public static int solution(int[] input){
    int longestIncreasingSubsequenceLen = 0;
    int N = input.length;
    int dp[] = new int[N];
    dp[0] = 1;

    for(int i = 1; i < N; i++){
      dp[i] = 1;
      int curr = input[i];
      int j = 0;
      while(j < i){
        if(curr > input[j]){
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
        j++;
      }
      if(dp[i] > longestIncreasingSubsequenceLen){
        longestIncreasingSubsequenceLen = dp[i];
      }
    }

    System.out.println("Longest Increasing subsequence length: "+ longestIncreasingSubsequenceLen);
    return longestIncreasingSubsequenceLen;
  }

  public static void main(String[] args){
    int[] input = new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
    solution(input);
  }
}
