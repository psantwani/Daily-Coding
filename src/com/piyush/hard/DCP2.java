package com.piyush.hard;

import java.util.Arrays;

/**
 * Daily Coding Problem: Problem #2 [Hard]
 *
 * This problem was asked by Uber.
 *
 * Given an array of integers, return a new array such that each element at index i of the new array is the product of all the numbers in the original array except the one at i.
 *
 * For example, if our input was [1, 2, 3, 4, 5], the expected output would be [120, 60, 40, 30, 24]. If our input was [3, 2, 1], the expected output would be [2, 3, 6].
 *
 * Follow-up: what if you can't use division?
 */

public class DCP2 {

  public static void main(String[] args) {
	int[] input = new int[]{1, 2, 3, 4, 5};
	solution(input);

	input = new int[]{3, 2, 1};
	solution(input);
  }

  private static int[] solution(int[] input){
    int n = input.length;
    int[] output = new int[n];

    int[] temp1 = new int[n];
    temp1[0] = 1;
    for(int i = 1; i < n; i++){
      temp1[i] = temp1[i-1] * input[i-1];
	}

	int[] temp2 = new int[n];
    temp2[n-1] = 1;
	for(int j = n - 2; j >= 0; j--){
		temp2[j] = temp2[j+1] * input[j+1];
	}

	for(int k = 0; k < n; k++){
	  output[k] = temp1[k] * temp2[k];
	}

	System.out.println(Arrays.toString(output));
    return output;
  }

}
