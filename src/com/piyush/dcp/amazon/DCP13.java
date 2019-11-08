package com.piyush.dcp.amazon;

import java.util.HashMap;
import java.util.Map;

/**
 This problem was asked by Amazon.

 Given an integer k and a string s, find the length of the longest substring that contains at most k distinct characters.

 For example, given s = "abcba" and k = 2, the longest substring with k distinct characters is "bcb".
 */
public class DCP13 {

  /**
   * Approach:
   * Take a map that contains the characters and corresponding count in the current substring,
   * if adding the next character increases the no. of distinct chars, start removing characters from the starting position
   * of the current substring till the new substring after appending the next character has k distinct characters.
   * At every step, calculate the length of the current substring, if it is greater the maxlength, update the maxlength.
   */
  private static int solution(String s, int k) {
    int lengthOfLongestSubs = 0;
    int n = s.length();

    if(n < k){
      return 0;
    }

    if(n == k){
      return 1;
    }

    Map<Character, Integer> map = new HashMap<>();
    int substringStartingIndex = 0;
    int substringEndingIndex = 0;

    for(int i = 0; i < n; i++){

      if(substringEndingIndex == n){
        break;
      }

      Character ch = s.charAt(i);

      Integer currCountOfCurrElement = map.getOrDefault(ch, 0);
      map.put(ch, currCountOfCurrElement + 1);

      if(map.keySet().size() > k){
        while(map.keySet().size() > k){
          Character substringFirstChar = s.charAt(substringStartingIndex);
          Integer currCountOfFirstElement = map.get(substringFirstChar);
          if(currCountOfFirstElement == 1){
            map.remove(substringFirstChar);
          }
          else{
            map.put(substringFirstChar, currCountOfFirstElement - 1);
          }
          substringStartingIndex++;
        }
      }

      substringEndingIndex++;

      Integer currLenOfSubstring = substringEndingIndex - substringStartingIndex;

      if(currLenOfSubstring > lengthOfLongestSubs){
        lengthOfLongestSubs = currLenOfSubstring;
      }
    }

    System.out.println("Length of longest substring: "+ lengthOfLongestSubs);
    return lengthOfLongestSubs;
  }

  public static void main(String[] args) {
    solution("abcba", 2);
    solution("abcbbba", 2);
    solution("abcbbbaaab", 2);
  }

}
