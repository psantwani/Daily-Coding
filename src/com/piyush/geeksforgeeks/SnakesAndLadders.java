package com.piyush.geeksforgeeks;


public class SnakesAndLadders {

  /**
   * Approach:
   * Create a DP array (1D) representing the min number of steps required to reach
   * the ith position (i -> 0 to N).
   */
  private static int solution(int[] moves, int N){
    int[] dp = new int[N];
    dp[0] = 1;
    dp[1] = 1;
    dp[2] = 1;
    dp[3] = 1;
    dp[4] = 1;
    dp[5] = 1;

    for(int i = 0; i < N; i++){

      int maxDiceRoll = 6;
      int temp = Integer.MAX_VALUE;

      while (maxDiceRoll > 0){
        if((i - maxDiceRoll >= 0) && dp[i - maxDiceRoll] != -1){
          int curr = dp[i - maxDiceRoll] + 1;
          if(curr < temp){
            temp = curr;
          }
        }

        maxDiceRoll--;
      }

      if(dp[i] == 0){
        dp[i] = temp;
      }

      if(dp[i] != 0 && temp < dp[i]){
        dp[i] = temp;
      }

      if(moves[i] != -1 && moves[i] > i){
        dp[moves[i]] = dp[i];
      }

      else if(moves[i] != -1 && moves[i] < i){
        dp[i] = -1;
      }

    }

    return dp[N-1];
  }

  public static void main(String[] args)
  {
    // Let us construct the board given in above diagram
    int N = 30;
    int moves[] = new int[N];
    for (int i = 0; i < N; i++)
      moves[i] = -1;

    // Ladders
    moves[2] = 21;
    moves[4] = 7;
    moves[10] = 25;
    moves[19] = 28;

    // Snakes
    moves[26] = 0;
    moves[20] = 8;
    moves[16] = 3;
    moves[18] = 6;

    System.out.println("Min Dice throws required is " +
        solution(moves, N));
  }

}
