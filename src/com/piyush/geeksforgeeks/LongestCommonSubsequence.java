package com.piyush.geeksforgeeks;

public class LongestCommonSubsequence {

  public int lcs(char[] word1, char[] word2, int m, int n){
    int[][] dp = new int[m][n];

    for(int i = 0; i < m; i++){
      char char1 = word1[i];
      for(int j = 0; j < n; j++){
         char char2 = word2[j];

         int top = 0, left = 0, diagonal = 0;
         if(i != 0 && j != 0){
           left = dp[i][j-1];
           top = dp[i-1][j];
           diagonal = dp[i-1][j-1];
         }

         if(char1 == char2){
           dp[i][j] = diagonal + 1;
         }
         else {
           dp[i][j] = Math.max(left, top);
         }
      }
    }

    return dp[m-1][n-1];
  }

  public static void main(String[] args)
  {
    LongestCommonSubsequence lcs = new LongestCommonSubsequence();
    String s1 = "AGGTAB";
    String s2 = "GXTXAYB";

    char[] X=s1.toCharArray();
    char[] Y=s2.toCharArray();
    int m = X.length;
    int n = Y.length;

    System.out.println("Length of LCS is" + " " +
        lcs.lcs( X, Y, m, n ) );
  }

}
