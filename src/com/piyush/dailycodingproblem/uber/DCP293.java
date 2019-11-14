package com.piyush.dailycodingproblem.uber;

import java.util.Arrays;

/**
 * This problem was asked by Uber.
 *
 * We have N (where N > 2) stones of various heights laid out in a row.
 * Task is to make a pyramid from given array of stones.
 * In a pyramid, height of the stones start from 1, increase by 1,
 * until it reaches some value x, then decreases by 1 until it reaches 1 again i.e.
 * the stones should be 1, 2, 3, 4…x – 1, x, x – 1, x – 2 … 1.
 * All other stones not part of the pyramid should have a height 0.
 * We cannot move any of the stones from their current position, however, by paying a fee of 1,
 * we can reduce the heights of the stones. We wish to minimize the cost of building a pyramid.
 * Output the minimum cost to build this pyramid.
 */
public class DCP293 {

  public static int solution(int[] input){
    int N = input.length;
    int[] outputArr = new int[N];
    int result = 0;
    int[] dp = new int[N];

    int mid;
    if(N%2 == 0){
      mid = N/2 - 1;
    }
    else{
      mid = N/2;
    }

    int decrementCounter = 1;

    for(int i = 0; i < N; i++){
      int curr = input[i];
      int expectedCurr;
      if(i <= mid){
        expectedCurr = i+1;
      }
      else{
        expectedCurr = i - 2*decrementCounter++ + 1;
      }

      dp[i] = expectedCurr - curr;
    }

    for(int i = 0 ; i < N; i++){
      outputArr[i] = input[i] + dp[i];
      result += Math.abs(dp[i]);
    }

    System.out.println(Arrays.toString(input));
    System.out.println(Arrays.toString(outputArr));
    System.out.println("Minimum cost: " + result);
    System.out.println("----------");

    return result;
  }

  public static void main(String[] args){
    int[] input = new int[]{1,2,3,4,2,1};
    solution(input);

    input = new int[]{1, 1, 3, 3, 2, 1};
    solution(input);

    input = new int[]{1,5,2};
    solution(input);

    input = new int[]{1,2,1};
    solution(input);

    input = new int[]{5,4,3,2,1};
    solution(input);

    input = new int[]{1,2,3,4,5,6};
    solution(input);

    input = new int[]{1,3,2,5,7,3,4,1,6,8};
    solution(input);
  }
}
