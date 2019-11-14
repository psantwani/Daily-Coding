package com.piyush.dailycodingproblem.miscellaneous;

/**
 * Given a matrix of 1s and 0s, return the number of "islands" in the matrix.
 * A 1 represents land and 0 represents water, so an island is a group of 1s that are neighboring and their perimiter is surrounded by water.
 *
 * For example, this matrix has 4 islands.
 *
 * 1 0 0 0 0
 * 0 0 1 1 0
 * 0 1 1 0 0
 * 0 0 0 0 0
 * 1 1 0 0 1
 * 1 1 0 0 1
 */

public class DCP84 {

  private static int solution(int[][] input){
    int noOfIslands = 0;
    int m = input.length;
    int n = input[0].length;

    for(int i = 0; i < m; i++){
      for(int j = 0; j < n; j++){
        int curr = input[i][j];
        if(curr == 0){
          continue;
        }
        dfs(input, m, n, i, j);
        noOfIslands++;
      }
    }

    System.out.println("Number of islands: " + noOfIslands);
    return noOfIslands;
  }

  private static void dfs(int[][] input, int m, int n, int i, int j){

    if(i > m - 1 || j > n - 1 || i < 0 || j < 0){
      return;
    }

    int curr = input[i][j];

    if(curr == 0){
      return;
    }

    input[i][j] = 0;

    dfs(input, m, n,i+1,j);
    dfs(input, m, n, i, j+1);
    dfs(input, m, n,i-1, j);
    dfs(input, m, n, i, j-1);

    return;
  }

  public static void main(String[] args){
    int[][] input = new int[6][5];
    input[0] = new int[]{1,0,0,0,0};
    input[1] = new int[]{0,0,1,1,0};
    input[2] = new int[]{0,1,1,0,0};
    input[3] = new int[]{0,0,0,0,0};
    input[4] = new int[]{1,1,0,0,1};
    input[5] = new int[]{1,1,0,0,1};


    solution(input);
  }

}
