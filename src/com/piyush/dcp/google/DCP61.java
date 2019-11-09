package com.piyush.dcp.google;

/**
 * This problem was asked by Google.
 *
 * Implement integer exponentiation. That is, implement the pow(x, y) function, where x and y are integers and returns x^y.
 *
 * Do this faster than the naive method of repeated multiplication.
 *
 * For example, pow(2, 10) should return 1024.
 */

public class DCP61 {

  static int solution(int x, int y){
    if(y == 0){
      return 1;
    }
    int mid = y/2;
    int leftY = mid;
    int rightY = y - mid;

    int left = solution(x, leftY);
    if(leftY == rightY){
      return left*left;
    } else{
      return left*left*x;
    }
  }

  public static void main(String[] args){
    System.out.println(solution(2, 10));
    System.out.println(solution(5, 9));
    System.out.println(solution(3, 13));
  }
}
