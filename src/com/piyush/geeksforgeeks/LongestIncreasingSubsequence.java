package com.piyush.geeksforgeeks;

import java.util.Arrays;
import java.util.HashMap;

public class LongestIncreasingSubsequence {

  static void lis(int[] input){
    int N = input.length;
    int[] dp = new int[N];
    dp[0] = 1;
    HashMap<Integer, Integer> map = new HashMap<>(); // child index: parent index
    map.put(0, 0);

    int maxCountIndex = 0;
    int maxCount = 0;

    for(int i = 1; i < N; i++){
      dp[i] = 1;
      int curr = input[i];
      int temp = 0;
      int tempIndex = -1;

      for(int j = 0; j < i; j++){
        int prev = input[j];
        if(curr > prev && dp[j] > temp){
          temp = dp[j];
          tempIndex = j;
        }
      }

      dp[i] = Math.max(dp[i], temp + 1);
      if(dp[i] > maxCount){
        maxCount = dp[i];
        maxCountIndex = i;
      }

      if(dp[i] > 1){
        map.put(i, tempIndex);
      } else {
        map.put(i, i);
      }
    }

    int lastIndexOfLIS = maxCountIndex;
    int[] result = new int[maxCount];
    while (maxCount > 0){
      int curr = input[lastIndexOfLIS];
      result[maxCount - 1]= curr;
      int parent = map.get(lastIndexOfLIS);
      if(parent == lastIndexOfLIS){
        break;
      }
      lastIndexOfLIS = parent;
      maxCount--;
    }

    System.out.println(Arrays.toString(result));

  }

  public static void main(String ... args){
    int arr[] = { 3, 2, 6, 4, 5, 1 };
    lis(arr);
    lis(new int[]{10, 22, 9, 33, 21, 50, 41, 60, 80});
  }

}
