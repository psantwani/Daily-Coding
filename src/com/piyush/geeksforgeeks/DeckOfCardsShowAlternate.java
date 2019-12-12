package com.piyush.geeksforgeeks;

import java.util.Arrays;

public class DeckOfCardsShowAlternate {

  private static void solution(int[] input){
    int N = input.length;
    int[] result = new int[N];
    int noOfCardsInsertInResult = 0;
    boolean insert = true;
    int indexOnInput = 0;
    int indexOnResult = 0;
    int smallestIndexForNextIteration = Integer.MAX_VALUE;
    int jumpFactor = 0;

    while (noOfCardsInsertInResult < N){
      if(insert){
        result[indexOnResult] = input[indexOnInput++];
        insert = false;
        noOfCardsInsertInResult++;
        if(indexOnResult == smallestIndexForNextIteration){
          smallestIndexForNextIteration = Integer.MAX_VALUE;
        }
      } else {
        if(indexOnResult < smallestIndexForNextIteration){
          smallestIndexForNextIteration = indexOnResult;
        }
        insert = true;
      }

      indexOnResult = (indexOnResult + (int)Math.pow(2,jumpFactor));
      if(indexOnResult > N - 1){
        indexOnResult = smallestIndexForNextIteration;
        jumpFactor++;
      }
    }

    System.out.println(Arrays.toString(result));
  }

  public static void main(String ... args){
    solution(new int[]{1,3,5,7,11,13,17});
    solution(new int[]{1,3,5,7});
    solution(new int[]{1,3,5,7,11,13,17,19,21});
    solution(new int[]{1,3,5,7,11,13,17,19,21,22});
  }
}