package com.piyush.dailycodingproblem.uber;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 * This problem was asked by Uber.
 *
 * Write a program that determines the smallest number of perfect squares that sum up to N.
 *
 * Here are a few examples:
 *
 * Given N = 4, return 1 (4)
 * Given N = 17, return 2 (16 + 1)
 * Given N = 18, return 2 (9 + 9)
 * Given N = 21, returns 3 (1 + 4 + 16)
 * Given N = 39, returns 4 (1 + 4 + 9 + 25)
 */
public class DCP350 {

  static void solution(int input){
    int smallNumberOfPerfectSquares = Integer.MAX_VALUE;
    int i = 1;

    List<Integer> squares = new ArrayList<>();
    while (i*i <= input){
      int curr = i*i;
      squares.add(curr);
	  ListIterator<Integer> itr = squares.listIterator(squares.size());

	  int temp = input;
	  int currNumberOfPerfectSquares = 0;
	  while (itr.hasPrevious() && temp > 0){
	    int num = itr.previous();
	    if(num > temp){
	      continue;
		}

		int quotient = temp/num;
	    int remainder = temp % num;
	    currNumberOfPerfectSquares += quotient;
	    temp = remainder;
	  }

	  if(currNumberOfPerfectSquares < smallNumberOfPerfectSquares){
	    smallNumberOfPerfectSquares = currNumberOfPerfectSquares;
	  }
      i++;
	}
  }

  public static void main(String ... args){
    solution(4);
    solution(17);
    solution(18);
	solution(21);
	solution(39);
  }
}
