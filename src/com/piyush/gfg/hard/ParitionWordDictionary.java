package com.piyush.gfg.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A dictionary of words is given. Any word is called valid, if it belongs to the dictionary.
 * A string is given. You need to find all partitions of string such that each partition is a valid word.
 * For eg. let the given dictionary be { man, ice, cream. go, icecream, mango } and string given to you is mangoicecream,
 * then all the valid breakdowns are :
 * man go ice cream
 * man go icecream
 * mango ice cream
 * mango icecream
 */
public class ParitionWordDictionary {

  /**
   * Approach
   * m a n g o i c e c r e  a  m
   * 0 1 2 3 4 5 6 7 8 9 10 11 12
   *
   * While iterating create a Linkedhashmap like -
   * {
   *   0: [2,4] // implying valid substrings [0,2] and [0,4]. valid substring = dictionary word
   *   2: [4] // note that 1 is not present in the map because no valid substring ends at pos 1.
   *   4: [7,12]
   *   7: [12]
   * }
   *
   * Then starting from 0, form all possible sequences
   * 0->2->4->7->12 (0->2 : man, 2->4 : go, 4->7 : ice, 7->12: cream) man, go, ice, cream
   * 0->2->4->12 : man, go, icecream
   * 0->4->7->12 : mango, ice, cream
   * 0->4->12 : mango, icecream
   */
  private static List<List<String>> solution(String input, Set<String> dict) {

    List<List<String>> result = new ArrayList<>();

    if(input == null || input == ""){
      return result;
    }

    int N = input.length();
    Map<Integer, List<Integer>> map = new LinkedHashMap<>();

    if(dict.contains(input.charAt(0))){
      map.put(0, new ArrayList<Integer>(){{add(0);}});
    }
    else{
      map.put(0, new ArrayList<Integer>(){});
    }

    for(int i = 1; i < N; i++){
      int j = 0;
      while (j < i){
        if(map.get(j) == null){
          j++;
          continue;
        }

        String substring = null;
        if(j == 0){
          substring = input.substring(0, i+1);
        }
        else{
          substring = input.substring(j+1, i+1);
        }

        if(dict.contains(substring)){
          List<Integer> curr = map.getOrDefault(j, new ArrayList<Integer>(){});
          curr.add(i);
          map.put(j, curr);
          map.put(i, new ArrayList<Integer>(){});
        }

        j++;
      }
    }

    Integer lastKey = (Integer) map.keySet().toArray()[map.size() -1];
    if(map.get(lastKey).size() == 0){
      map.remove(lastKey);
    }

    recursive(map, 0, input, new ArrayList<String>(){}, result);

    for(List<String> combination : result){
      System.out.println(Arrays.toString(combination.toArray()));
    }

    return result;
  }

  static void recursive(Map<Integer, List<Integer>> map, int num, String input, List<String> currList, List<List<String>> masterList){

    List<Integer> targets = map.get(num);
    if(targets == null || targets.size() == 0){
      if(currList.size() != 0){
        masterList.add(new ArrayList<>(currList));
      }
      return;
    }

    int index = 0;
    while (index < targets.size()){
      int target = targets.get(index);
      int startingIndex = num == 0 ? 0 : num + 1;
      int endingIndex = target + 1;
      String substring = input.substring(startingIndex, endingIndex);
      currList.add(substring);
      recursive(map, target, input, currList, masterList);
      currList.remove(substring);
      index++;
    }

  }

  public static void main(String[] args) {
    String[] dictArr = new String[]{"man", "ice", "cream", "go", "icecream", "mango"};
    Set<String> dict = new HashSet<>();
    dict.addAll(Arrays.asList(dictArr));
    String input = "mangoicecream";
    solution(input, dict);

    input = "GeeksQuiz";
    solution(input, dict);
  }
}
