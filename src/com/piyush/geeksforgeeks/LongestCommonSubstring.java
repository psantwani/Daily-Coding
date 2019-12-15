package com.piyush.geeksforgeeks;

public class LongestCommonSubstring {

  static int LCSubStr(char[] a, char[] b, int m, int n){
    int[][] dp = new int[m][n];
    int result = 0;

    for(int i = 0; i < m; i++){
      char char1= a[i];
      for (int j = 0; j < n; j++){
        char char2 = b[j];
        int diagonal = 0;
        if(i != 0 && j != 0){
          diagonal = dp[i-1][j-1];
        }
        if(char1 == char2){
          dp[i][j] = diagonal + 1;
        }
        if(dp[i][j] > result){
          result = dp[i][j];
        }
      }
    }
    return result;
  }

  public static void main(String[] args)
  {
    String X = "OldSite:GeeksforGeeks.org";
    String Y = "NewSite:GeeksQuiz.com";

    int m = X.length();
    int n = Y.length();

    System.out.println("Length of Longest Common Substring is "
        + LCSubStr(X.toCharArray(), Y.toCharArray(), m, n));
  }
}
