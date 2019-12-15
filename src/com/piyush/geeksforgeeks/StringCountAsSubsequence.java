package com.piyush.geeksforgeeks;

// Time complexity : O(m*n). Space complexity : O(1)
public class StringCountAsSubsequence {

  /**
   * Approach -
   * Go from right to left and mark the last occurence of each char in string
   * Use as many pointers as number of characters in subsequence string. For first iterator, start with
   * the last pointer. Set s first then k and then G.
   * Gks and Geeksfor"G"ee"k""s"
   * For subsequent iterations, start with the first pointer. Find the next G, add to the result count,
   * then find the next k and then s and so on..
   */
  static int count(String input, String searchString){
    int result = 0;
    int M = input.length();
    int N = searchString.length();
    int[] pointers = new int[N];

    for(int i = 0; i < N; i++){
      pointers[i] = M;
    }

    int firstCounter = 0;
    for(int i = N - 1; i >= 0; i--){
      char curr = searchString.charAt(i);
      int j = pointers[i] - 1;
      while (j >= 0){
        if(input.charAt(j) == curr && (i == N - 1 || j < pointers[i+1])){
          pointers[i] = j;
          firstCounter++;
          break;
        }
        j--;
      }
    }

    if(firstCounter != N){
      return 0;
    } else {
      result++;
    }

    int lastIndexMoved = 0;
    while (true){
      boolean resultUpdated = false;

      for(int i = lastIndexMoved; i < N; i++){
        char curr = searchString.charAt(i);
        int j = pointers[i] - 1;
        int before = pointers[i];
        while (j >= 0){
          if(input.charAt(j) == curr && (i == 0 || j > pointers[i-1])){
            pointers[i] = j;
            break;
          }
          j--;
        }

        if(before == pointers[i]){
          break;
        } else {
          resultUpdated = true;
          result++;
          lastIndexMoved = (i + 1) % N;
        }
      }

      if(!resultUpdated){
        break;
      }

    }

    return result;
  }

  public static void main (String[] args)
  {
    String a = "GeeksforGeeks";
    String b = "Gks";

    System.out.println(count(a, b));
  }

}
