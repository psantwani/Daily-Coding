package com.piyush.dailycodingproblem.palantir;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This problem was asked by Palantir.
 *
 * Given a number represented by a list of digits, find the next greater permutation of a number,
 * in terms of lexicographic ordering. If there is not greater permutation possible,
 * return the permutation with the lowest value/ordering.
 *
 * For example, the list [1,2,3] should return [1,3,2].
 * The list [1,3,2] should return [2,1,3].
 * The list [3,2,1] should return [1,2,3].
 *
 * Can you perform the operation without allocating extra memory (disregarding the input memory)?
 */
public class DCP95 {

  static Integer[] solution(Integer[] input){
    int N = input.length;
    int index = N - 1;
    while (index > 0){
      if(input[index - 1] > input[index]){
        index--;
        continue;
      }

      int indexWithSmallValue = index - 1;
      int carry = input[indexWithSmallValue];
      int min = input[N - 1];
      input[indexWithSmallValue] = min;
      int max = input[index];
      input[index] = carry;
      if(index != (N - 1)){
        input[N - 1] = max;
      }
      int leftPtr = index + 1;
      int rightPtr = N - 2;
      while(leftPtr < rightPtr){
        int temp = input[leftPtr];
        input[leftPtr] = input[rightPtr];
        input[rightPtr] = temp;
        leftPtr++;
        rightPtr--;
      }

      System.out.println(Arrays.toString(input));
      return input;
    }

    Arrays.sort(input);
    System.out.println(Arrays.toString(input));
    return input;
  }

  public static void main(String[] args){
    solution(new Integer[]{2,1,8,7,6,5});
    solution(new Integer[]{1,2,3});
    solution(new Integer[]{1,3,2});
    solution(new Integer[]{3,2,1});
    solution(new Integer[]{1,2,3,4});
    solution(new Integer[]{4,3,2,1});
    solution(new Integer[]{5,3,4,9,7,6});
  }
}
