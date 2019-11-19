package com.piyush.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Maximal Rectangle
 * <p>
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * Output: 6
 */
public class LC85 {

  public static int solution(int[][] input) {
    int M = input.length;
    int N = input[0].length;

    List<List<Integer>> horizontalLines;
    Map<Integer, List<List<Integer>>> horizontalMap = new HashMap<>();
    List<Integer> currHorizontalPair;

    List<List<Integer>> verticalLines = new ArrayList<>();
    Map<Integer, List<List<Integer>>> verticalMap = new HashMap<>();
    List<Integer> currVerticalPair = new ArrayList<>();


    for (int i = 0; i < M; i++) {
      horizontalLines = new ArrayList<>();
      currHorizontalPair = new ArrayList<>();

      for (int j = 0; j < N; j++) {

        int curr = input[i][j];

        if (curr == 1) {
          currHorizontalPair.add(j);
          continue;
        }

        if (currHorizontalPair.size() > 0) {
          horizontalLines.add(currHorizontalPair);
        }

        currHorizontalPair = new ArrayList<>();

      }

      if (currHorizontalPair.size() > 0) {
        horizontalLines.add(currHorizontalPair);
      }

      horizontalMap.put(i, horizontalLines);
    }

    for (int i = 0; i < N; i++) {
      verticalLines = new ArrayList<>();
      currVerticalPair = new ArrayList<>();

      for (int j = 0; j < M; j++) {

        int curr = input[j][i];

        if (curr == 1) {
          currVerticalPair.add(j);
          continue;
        }

        if (currVerticalPair.size() > 0) {
          verticalLines.add(currVerticalPair);
        }

        currVerticalPair = new ArrayList<>();

      }

      if (currVerticalPair.size() > 0) {
        verticalLines.add(currVerticalPair);
      }

      verticalMap.put(i, verticalLines);
    }



    return 0;
  }



  public static void main(String[] args) {
    int[][] input = new int[4][5];
    input[0] = new int[] {1, 0, 1, 0, 0};
    input[1] = new int[] {1, 0, 1, 1, 1};
    input[2] = new int[] {1, 1, 1, 1, 1};
    input[3] = new int[] {1, 0, 0, 1, 0};
    solution(input);
  }
}
