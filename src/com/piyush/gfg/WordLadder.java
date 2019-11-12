package com.piyush.gfg;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a dictionary, and two words ‘start’ and ‘target’ (both of same length).
 * Find length of the smallest chain from ‘start’ to ‘target’ if it exists,
 * such that adjacent words in the chain only differ by one character and each word
 * in the chain is a valid word i.e., it exists in the dictionary.
 * It may be assumed that the ‘target’ word exists in dictionary and length of all dictionary words is same.
 */
public class WordLadder {

  static class WordInfo{
    String word;
    int chainPos;

    public WordInfo(String word, int chainPos){
      this.word = word;
      this.chainPos = chainPos;
    }
  }

  public static int solution(String start, String target, String[] dictionary){
    int shortestPathLen = 0;
    int N = dictionary.length;
    boolean[] visited = new boolean[N];

    WordInfo wordInfo = new WordInfo(start, 0);
    Queue<WordInfo> queue = new LinkedList<>();
    queue.add(wordInfo);

    while (!queue.isEmpty()){
      WordInfo currWordInfo = queue.poll();
      String currWord = currWordInfo.word;
      if(currWord == target){
        shortestPathLen = currWordInfo.chainPos + 1;
        break;
      }

      for(int i = 0; i < N; i++){
        String dictionaryWord = dictionary[i];
        if(!visited[i] && isAdjacent(currWord, dictionaryWord)){
          visited[i] = true;
          queue.add(new WordInfo(dictionaryWord, currWordInfo.chainPos + 1));
        }
      }
    }

    System.out.println("Shortest path length: " + shortestPathLen);
    return shortestPathLen;
  }

  private static boolean isAdjacent(String a, String b){
    int diff = 0;
    for(int i = 0; i < a.length(); i++){ // since both a and b have the same length.
      if(a.charAt(i) != b.charAt(i)){
        diff++;
      }
    }

    return diff == 1;
  }



  public static void main(String[] args){
    String start = "hit";
    String end = "cog";
    String[] dictionary = new String[]{"hot","dot","dog","lot","log","cog"};
    solution(start, end, dictionary);

    start = "TOON";
    end = "PLEA";
    dictionary = new String[]{"POON", "PLEE", "SAME", "POIE", "PLEA", "PLIE", "POIN"};
    solution(start, end, dictionary);
  }

}
