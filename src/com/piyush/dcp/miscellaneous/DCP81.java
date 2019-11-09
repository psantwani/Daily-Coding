package com.piyush.dcp.miscellaneous;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a mapping of digits to letters (as in a phone number), and a digit string,
 * return all possible letters the number could represent.
 *
 * For example if {“2”: [“a”, “b”, “c”], 3: [“d”, “e”, “f”], …}
 * then “23” should return [“ad”, “ae”, “af”, “bd”, “be”, “bf”, “cd”, “ce”, “cf"].
 */
public class DCP81 {

  static Map<Character, Character[]> map = new HashMap<>();

  private static List<String> solution(String input){
    List<String> result = new ArrayList<>();

    StringBuilder sb = new StringBuilder();
    recursive(input, 0, "", result);

    System.out.println(Arrays.toString(result.toArray()));
    return result;
  }

  static void recursive(String input, int digitPos, String currSeq, List<String> result){

    if(digitPos >= input.length()){
      result.add(currSeq);
      return;
    }

    Character currDigit = input.charAt(digitPos);
    Character[] digitLetters = map.get(currDigit);
    int N = digitLetters.length;

    for(int i = 0; i < N; i++){
      Character currChar = digitLetters[i];
      String temp = currSeq;
      currSeq += currChar;
      recursive(input, digitPos + 1, currSeq, result);
      currSeq = temp;
    }
  }

  public static void main(String[] args){
    map.put('2', new Character[]{'A','B','C'});
    map.put('3', new Character[]{'D','E','F'});
    map.put('4', new Character[]{'G','H','I'});
    map.put('5', new Character[]{'J','K','L'});
    map.put('6', new Character[]{'M','N','O'});
    map.put('7', new Character[]{'P','Q','R', 'S'});
    map.put('8', new Character[]{'T','U','V'});
    map.put('9', new Character[]{'W','X','Y','Z'});

    String input = "23";
    solution(input);

    input = "49";
    solution(input);

    input = "7";
    solution(input);

    input = "568";
    solution(input);
  }

}
