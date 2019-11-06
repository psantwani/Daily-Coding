package com.piyush.dcp.hard;

/**
 * This problem was asked by Amazon.
 *
 * There exists a staircase with N steps, and you can climb up either 1 or 2 steps at a time. Given N,
 * write a function that returns the number of unique ways you can climb the staircase. The order of the steps matters.
 *
 * For example, if N is 4, then there are 5 unique ways:
 *
 * 1, 1, 1, 1
 * 2, 1, 1
 * 1, 2, 1
 * 1, 1, 2
 * 2, 2
 * What if, instead of being able to climb 1 or 2 steps at a time, you could climb any number from
 * a set of positive integers X? For example, if X = {1, 3, 5}, you could climb 1, 3, or 5 steps at a time.
 */
public class DCP12 {

  private static int solution(int N) {

    if(N == 1){
      return 1;
    }

    if(N == 2){
      return 2;
    }

    int dp[] = new int[N+1];
    dp[0] = 0;
    dp[1] = 1;
    dp[2] = 2;

    for(int i = 3; i <= N; i++){
      dp[i] = dp[i-1] + dp[i-2];
    }

    System.out.println("No of ways : " + dp[N]);
    return dp[N];
  }

  public static void main(String[] args) {
    solution(4);
  }

}
