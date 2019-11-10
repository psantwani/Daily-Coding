package com.piyush.dcp.facebook;

/**
 * This problem was asked by Facebook.
 *
 * Given an integer n, find the next biggest integer with the same number of 1-bits on.
 * For example, given the number 6 (0110 in binary), return 9 (1001).
 */
public class DCP338 {

  static int solution(int input){

    int binaryLen = 0;
    while(input >= Math.pow(2, binaryLen)){
      binaryLen++;
    }

    int cardinality = deriveCardinality(input);
    int lsbPosition = deriveLSB(input);
    int msbPosition = deriveMSB(input, binaryLen);

    if(lsbPosition == msbPosition){
      return input << 1;
    }

    while (lsbPosition < msbPosition){
      if((lsbPosition + 1) < msbPosition && !checkIfBitSet(input, lsbPosition + 1)){
        input = unsetBit(input, lsbPosition);
        input = setBit(input, lsbPosition + 1);
        return input;
      }
      lsbPosition++;
    }

    int newNum = 1 << msbPosition + 1;
    cardinality = cardinality - 1;
    int counter = 0;
    while(cardinality > 0){
      newNum = setBit(newNum, counter);
      counter++;
      cardinality--;
    }

    return newNum;
  }

  private static int deriveCardinality(int input){
    int cardinality = 0;
    while(input>0){
      ++cardinality;
      input &= input-1;
    }
    return cardinality;
  }

  private static int deriveLSB(int input){
    // eg. input = 18 (10010)
    int a = input - 1; // sets all bits before lsb : 10001
    int b = input | a; // 10011
    int lsbNo = a ^ b; // LSB number. only difference between a and b is the lsb bit..
    return (int) Math.floor(Math.log(lsbNo)/Math.log(2)); // LSB position 1.
  }

  private static int deriveMSB(int input, int binaryLen){
    // eg. input = 18 (10010)
    int counter = 0;
    while (counter < binaryLen){
      input |= (1 << counter); // setting all bits one by one. i.e. 11111
      counter++;
    }
    int a = input + 1; // Un sets bit except MSB. 100000
    int msbNo = a >> 1; // MSB number 10000
    return  (int) Math.floor(Math.log(msbNo)/Math.log(2)); // MSB position 4.
  }

  private static boolean checkIfBitSet(int input, int pos){
    return (input & (1 << pos)) != 0;
  }

  private static int unsetBit(int input, int pos){
    input &= ~( 1 << pos);
    return input;
  }

  private static int setBit(int input, int pos){
    input |= (1 << pos);
    return input;
  }


  public static void main(String[] args){
    int input = 8;
    System.out.println(solution(input));

    input = 6;
    System.out.println(solution(input));

    /**
     * input:  1011010
     * output: 1011100
     * input:  1011110
     * output: 1111100
     * input:  1110000
     * output: 10000011
     */
  }
}