package com.piyush.gfg.easy;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Given a string, find the first non-repeating character in it.
 * For example, if the input string is “GeeksforGeeks”, then output should be ‘f’
 * and if input string is “GeeksQuiz”, then output should be ‘G’.
 */
public class FirstNonRepeatingChar {

  private static Character solution(String s) {
    LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
    int n = s.length();

    for(int i = 0; i < n; i++){
      Character currChar = s.charAt(i);
      int currentCount = map.getOrDefault(currChar, 0);
      map.put(currChar, currentCount + 1);
    }

    for(Map.Entry entry : map.entrySet()){
      if(entry.getValue().equals(1)){
        System.out.println("First non-repeating character: " + entry.getKey().toString());
        return (Character) entry.getKey();
      }
    }

    return null;
  }

  public static void main(String[] args) {
    String input = "GeeksforGeeks";
    solution(input);

    input = "GeeksQuiz";
    solution(input);
  }
}
