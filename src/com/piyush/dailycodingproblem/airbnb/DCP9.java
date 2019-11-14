package com.piyush.dailycodingproblem.airbnb;

/**
 * Daily Coding Problem: Problem #9 [Hard]
 *
 * This problem was asked by Airbnb.
 *
 * Given a list of integers, write a function that returns the largest sum of non-adjacent numbers. Numbers can be 0 or negative.
 *
 * For example, [2, 4, 6, 2, 5] should return 13, since we pick 2, 6, and 5. [5, 1, 1, 5] should return 10, since we pick 5 and 5.
 *
 * Follow-up: Can you do this in O(N) time and constant space?
 */
public class DCP9 {

  /**
   * Approach is same as the house robber problem.
   */
  private static int solution(int[] input) {
    if(input == null || input.length == 0){
      return 0;
	}

	if(input.length == 1){
	  return input[0];
	}

	if(input.length == 2){
	  Math.max(input[0], input[1]);
	}

	int[] dp = new int[input.length + 1];
	dp[0] = input[0];
	dp[1] = Math.max(input[0], input[1]);

	for(int i = 2 ; i < input.length; i++){
	  dp[i] = Math.max(dp[i-2] + input[i], dp[i-1]);
	}

	int maxSum = Integer.MIN_VALUE;
	for(int k = 0 ; k < dp.length; k++){
	  if(dp[k] > maxSum){
	    maxSum = dp[k];
	  }
	}

	System.out.println("Max sum: " + maxSum);
	return maxSum;
  }

  public static void main(String[] args) {
	int[] input = new int[] {2,4,6,2,5};
	solution(input);

	input = new int[] {5,1,1,5};
	solution(input);
  }
}
