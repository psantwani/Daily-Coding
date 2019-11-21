package com.piyush.dailycodingproblem.uber;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This problem was asked by Uber.
 *
 * One way to unlock an Android phone is through a pattern of swipes across a 1-9 keypad.
 *
 * For a pattern to be valid, it must satisfy the following:
 *
 * All of its keys must be distinct.
 * It must not connect two keys by jumping over a third key, unless that key has already been used.
 * For example, 4 - 2 - 1 - 7 is a valid pattern, whereas 2 - 1 - 7 is not.
 *
 * Find the total number of valid unlock patterns of length N, where 1 <= N <= 9.
 */
public class DCP239 {

  static int solution(int input){
    int validUnlockPatternCount = 0;
    int[][] dialPad = new int[3][3];
	dialPad[0] = new int[]{1,2,3};
	dialPad[1] = new int[]{4,5,6};
	dialPad[2] = new int[]{7,8,9};

    for(int i = 0 ; i < 3; i++){
      for(int j = 0; j < 3; j++){
		Set<Integer> curr = new LinkedHashSet<>();
		validUnlockPatternCount += recursive(input, i,j, dialPad, curr);
	  }
	}

	System.out.println("total number of valid unlock patterns : " + validUnlockPatternCount);
    return validUnlockPatternCount;
  }

  private static int recursive(int N, int i, int j, int[][] dialPad, Set<Integer> currNums){

    if(i > 2 || i < 0 || j > 2 || j < 0){
		return 0;
	}

	if(currNums.contains(dialPad[i][j])){
	  return 0;
	}

	currNums.add(dialPad[i][j]);

	if(currNums.size() == N){
	  // System.out.println(Arrays.toString(currNums.toArray())); // Print the sequence.
	  removeElementFromSet(i, j, dialPad, currNums);
	  return 1;
	}


	int a = recursive(N, i, j+1, dialPad, currNums);

	int b = recursive(N, i, j-1, dialPad, currNums);

	int c = recursive(N, i+1, j, dialPad, currNums);

	int d = recursive(N, i-1, j, dialPad, currNums);

	int e = recursive(N, i-1, j-1, dialPad, currNums);

	int f = recursive(N, i-1, j+1, dialPad, currNums);

	int g = recursive(N, i+1, j+1, dialPad, currNums);

	int h = recursive(N, i+1, j-1, dialPad, currNums);

	removeElementFromSet(i, j, dialPad, currNums);

    return a + b + c + d + e + f + g + h;
  }

  private static void removeElementFromSet(int i, int j, int[][] dialPad, Set<Integer> currNums){
    if(i >= 0 && j >= 0 && i <= 2 && j <=2){
      currNums.remove(dialPad[i][j]);
	}
  }

  public static void main(String ... args){
	solution(2);
	solution(3);
	solution(4);
	solution(5);
	solution(6);
	solution(7);
  }
}
