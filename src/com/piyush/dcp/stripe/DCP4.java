package com.piyush.dcp.stripe;

import java.util.Arrays;

/**
 * Daily Coding Problem: Problem #4 [Hard]
 *
 * This problem was asked by Stripe.
 *
 * Given an array of integers, find the first missing positive integer in linear time and constant space. In other words, find the lowest positive integer that does not exist in the array. The array can contain duplicates and negative numbers as well.
 *
 * For example, the input [3, 4, -1, 1] should give 2. The input [1, 2, 0] should give 3.
 *
 * You can modify the input array in-place.
 */

public class DCP4 {

  /**
   * Approach : 1. First set all the negative numbers on the left.
   * After step 1, {3, 4, 4, -1, -1, 1, 1, 3} becomes {-1, -1, 4, 3, 4, 1, 1, 3}
   * 2. Consider only the subset of the array (first element as index = 1) now with all positive integers - {4, 3, 4, 1, 1, 3}
   * If this array was sorted, 4 would've been on the 4th index , 3 would've been on 3rd index and so on.
   * Iterating over this subarray, negate the number on the index where this number would've been if it were sorted.
   * After step 2, the array becomes {-1, -1, -4, 3, -4, -1, 1, 3}
   * 3. Find the first occurence of a postive number in that subarray {-4, 3, -4, -1, 1, 3}.
   * The answer is index 2 (again, its an array starting with index 1). That's the first missing positive number. 2
   *
   */
  static int solution(int[] input){
    int negativeNumPointer = 0;
    int n = input.length;

    for(int i = 0; i < n; i++){
      int val = input[i];
      if(val < 0 && i > negativeNumPointer){
        int temp = input[negativeNumPointer];
        input[negativeNumPointer] = val;
        input[i] = temp;
        while (negativeNumPointer < n && input[negativeNumPointer] < 0){
          negativeNumPointer++;
        }
      }
    }

    System.out.println("Array after pushing negative to left: " + Arrays.toString(input));

    if(input[negativeNumPointer] == 0){
      negativeNumPointer++;
    }

    System.out.println("Positive starts from: " + negativeNumPointer);


    for(int j = negativeNumPointer; j < n; j++){
      int absVal = Math.abs(input[j]);
      int actualPosition = negativeNumPointer + absVal - 1;
      if(actualPosition < n){
        input[actualPosition] = Math.abs(input[actualPosition]) * -1;
      }
    }

    System.out.println("Array after negating positive numbers: " + Arrays.toString(input));


    int firstPositiveMissingNumber = Integer.MAX_VALUE;

    for(int k = negativeNumPointer; k < n; k++){
      if(input[k] > 0){
        firstPositiveMissingNumber = k - 1;
        break;
      }
    }

    System.out.println("First Positive Missing Number: " + firstPositiveMissingNumber);
    System.out.println("-----------------");
    return firstPositiveMissingNumber;
  }

  public static void main(String[] args) {
    int[] input = new int[]{3, 4, 4, -1, -1, 1, 1, 3};
    solution(input);

    input = new int[]{2, 4, 6, -8, -1, 1, 5, 3};
    solution(input);
  }

}
