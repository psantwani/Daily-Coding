package com.piyush.oodesign.ShuffleArray;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Shuffle an Array. Shuffle a set of numbers without duplicates.
 */
public class ShuffleArrayMain {

  public static void main(String ... args){
    int[] nums = {1,2,3,4};
    Solution solution = new Solution(nums);
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
    System.out.println(Arrays.toString(solution.shuffle()));
  }

}

class Solution{

  private int[] nums;
  private int size;
  private Random random = new Random();
  private int[] permutationsPossibleAtPosition;
  private LinkedHashSet<Integer> numberSet;
  private HashMap<Integer, int[]> permutations;

  public Solution(int[] nums){
    this.nums = nums;
    this.size = nums.length;
    this.permutations = new HashMap<>();
    this.numberSet = new LinkedHashSet<>();
    permutationsPossibleAtPosition = new int[this.size];
    for(int i = 0; i < this.size; i++){
      numberSet.add(nums[i]);
      permutationsPossibleAtPosition[i] = getFactorial(this.size - i - 1);
    }
  }

  private int getFactorial(int num){
    if(num == 0){
      return 0;
    }

    if(num == 1){
      return 1;
    }

    return num*getFactorial(num - 1);
  }

  public int[] shuffle(){
    int result[] = new int[this.size];

    int permutationNumber = random.nextInt(getFactorial(nums.length));
    System.out.println("Getting permutation number " + permutationNumber);
    if(this.permutations.get(permutationNumber) != null){
      System.out.println("Found in cache. Returning..");
      return this.permutations.get(permutationNumber);
    }

    HashSet<Integer> availableNumbers = new HashSet<>(this.numberSet);
    int i = 0;
    int j = 0;
    int temp = permutationNumber;

    while (j <= this.size - 1 && temp != 0){
      int permutationsPossibleAtIndex = this.permutationsPossibleAtPosition[i++];
      int section = temp / permutationsPossibleAtIndex;
      int element = getNthElementInSet(availableNumbers, section);
      result[j++] = element;
      availableNumbers.remove(element);
      temp = temp % permutationsPossibleAtIndex;
    }

    for (Integer availableNumber : availableNumbers) {
      result[j++] = availableNumber;
    }

    this.permutations.put(permutationNumber, result);
    return result;
  }

  public void reset(){
    // do nothing.
  }

  private int getNthElementInSet(Set<Integer> set, int n){
    Iterator<Integer> itr = set.iterator();

    for(int i = 0; itr.hasNext(); i++) {
      int element = itr.next();
      if (i == n) {
        return element;
      }
    }

    return -1;
  }

}

//0   1234
//1   1243
//2   1324
//3   1342
//4   1423
//5   1432
//
//6   2134
//7   2143
//8   2314
//9   2341
//10  2413
//11  2431

//12  3124
//13  3142
//14  3214
//15  3241
//16  3412
//17  3421

//18  4123
//19  4132
//20  4213
//21  4231
//22  4312
//23  4321