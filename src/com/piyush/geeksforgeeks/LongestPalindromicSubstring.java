package com.piyush.geeksforgeeks;

import java.util.HashSet;

public class LongestPalindromicSubstring {

  static int longestPalSubstr(String input){
    int m = input.length();
    boolean[][] dp = new boolean[m][m];
    HashSet<String> longestPalSub = new HashSet<>();

    for(int i = 0; i < m; i++){
      dp[i][i] = true;
      longestPalSub.add(String.valueOf(input.charAt(i)));
    }

    for(int i = 2; i < m; i++){ // length of substring
      int left = 0;
      int right = left + i - 1;
      HashSet<String> temp = new HashSet<>();
      while (right < m){
        if(input.charAt(left) == input.charAt(right)){
          dp[left][right] = right - left == 1 || dp[left + 1][right - 1];
          if(dp[left][right]){
            temp.add(input.substring(left, right+1));
          }
        }
        left++;
        right++;
      }
      if(temp.size() > 0){
        longestPalSub = temp;
      }
    }

    return longestPalSub.iterator().next().length();
  }

  public static void main(String[] args) {
    String str = "forgeeksskeegfor";
    System.out.println("Length is: " +
        longestPalSubstr(str));
  }

}
