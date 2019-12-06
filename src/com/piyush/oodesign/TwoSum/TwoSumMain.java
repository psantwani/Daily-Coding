package com.piyush.oodesign.TwoSum;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Design and implement a TwoSum class. It should support the following operations: add and find.
 *
 * add – Add the number to an internal data structure.
 * find – Find if there exists any pair of numbers which sum is equal to the value.
 */
public class TwoSumMain {

  public static void main(String ... args){
    TwoSum twoSum = new TwoSum();
    twoSum.add(1);
    twoSum.add(3);
    twoSum.add(5);
    twoSum.add(5);
    System.out.println(twoSum.find(4));
    System.out.println(twoSum.find(7));
    System.out.println(twoSum.find(10));
  }

}

class TwoSum{

  private ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

  public void add(int element){
    int currCount = map.getOrDefault(element, 0);
    map.put(element, currCount + 1);
  }

  public boolean find(int sum){
    boolean exists = false;

    for(Integer key : map.keySet()){
      int diff = sum - key;
      int currCount = map.get(key);

      if(currCount - 1 == 0){
        map.remove(key);
      } else{
        map.put(key, currCount - 1);
      }

      if(map.containsKey(diff)){
        exists = true;
      }

      map.put(key, currCount);

      if(exists){
        break;
      }
    }

    return exists;
  }

}