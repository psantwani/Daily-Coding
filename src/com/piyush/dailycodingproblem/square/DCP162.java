package com.piyush.dailycodingproblem.square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This problem was asked by Square.
 *
 * Given a list of words, return the shortest unique prefix of each word. For example, given the list:
 *
 * dog
 * cat
 * apple
 * apricot
 * fish
 * Return the list:
 *
 * d
 * c
 * app
 * apr
 * f
 */

public class DCP162 {

  static class Data{
    List<Integer> index;
    int count;
    String prefix;

    Data(List<Integer> index, int count, String prefix){
      this.index = index;
      this.count = count;
      this.prefix = prefix;
    }
  }

  public static String[] solution(String[] input){
    int N = input.length;
    String[] result = new String[N];
    Map<String, List<Integer>> map = new ConcurrentHashMap<>();

    for(int i = 0; i < N; i++){
      updateMap(input, i,"", 0, map);
    }

    Iterator it = map.entrySet().iterator();
    while (it.hasNext()){
      Map.Entry entry = (Map.Entry) it.next();
      String key = (String) entry.getKey();
      List<Integer> values = (List<Integer>) entry.getValue();
      if(values.size() == 1){
        result[values.get(0)] = key;
      } else {
        for(int i = 0; i < values.size(); i++){
          updateMap(input, values.get(i), key, key.length(), map);
        }
      }
      map.remove(key);
      it = map.entrySet().iterator();
    }


    System.out.println(Arrays.toString(result));
    return result;
  }

  private static void updateMap(String[] input, int index, String currString, int charPos, Map<String, List<Integer>> map){
    String currWord = input[index];
    String currPrefix = currString + currWord.charAt(charPos);
    List<Integer> currIndexesWithCurrPrefix = map.getOrDefault(currPrefix, new ArrayList<Integer>());
    currIndexesWithCurrPrefix.add(index);
    map.put(currPrefix, currIndexesWithCurrPrefix);
  }

  public static void main(String[] args){
    String[] input = new String[]{"dog", "cat", "apple", "apricot", "fish"};
    solution(input);

    input = new String[]{"india", "indonesia", "australia", "austria", "germany", "france"};
    solution(input);
  }

}
