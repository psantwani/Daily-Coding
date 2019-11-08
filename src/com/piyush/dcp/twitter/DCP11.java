package com.piyush.dcp.twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Daily Coding Problem: Problem #11 [Medium]
 *
 * This problem was asked by Twitter.
 *
 * Implement an autocomplete system. That is, given a query string s and a set of all possible query strings, return all strings in the set that have s as a prefix.
 *
 * For example, given the query string de and the set of strings [dog, deer, deal], return [deer, deal].
 */
public class DCP11 {

  /**
   * Using Trie data structure.
   * ROOT
   * isEnd:false
   * abcdefghijklmnopqrstuvwxyz
   *    |
   *   link
   *    |
   * Node
   * isEnd:false
   * ....
   *
   * Create a hashmap of links.
   * During searchPrefix, iternate through each till all entries at all levels are explored.
   */
  static class TrieNode{

    boolean isEnd;
    Map<Character, TrieNode> links;

    public TrieNode(){
      links = new HashMap<>();
	}

	public boolean containsKey(char ch){
      return links.containsKey(ch);
    }

	public void put(char ch, TrieNode node){
      links.put(ch, node);
    }

    public TrieNode get(char ch){
      return links.getOrDefault(ch, null);
    }

    void setEnd(){
      isEnd = true;
	}

    boolean isEnd(){
      return isEnd;
	}
  }

  static class Trie{

    TrieNode root;

    public Trie(){
      root = new TrieNode();
    }

    public void insert(String s){
      int n = s.length();
      TrieNode node = root;
      for(int i = 0 ; i < n; i++){
        char c = s.charAt(i);
        if(!node.containsKey(c)){
          node.put(c, new TrieNode());
        }
        node = node.get(c);
      }
      node.setEnd();
    }

    public List<String> prefixSearch(String prefix){
      List<String> matchingResult = new ArrayList<>();
      int n = prefix.length();
      TrieNode node = root;
      for(int i = 0 ; i < n; i++){
        char c = prefix.charAt(i);
        if(!node.containsKey(c) && !node.isEnd){
          break;
        }

        node = node.get(c);
      }

      if(node.isEnd){
        matchingResult.add(prefix);
      }

      Map<Character, TrieNode> links = node.links;
      Set<Map.Entry<Character, TrieNode>> entrySet = links.entrySet();
      Iterator<Map.Entry<Character, TrieNode>> iterator = entrySet.iterator();
      while (iterator.hasNext()){
        prefixRecursion(iterator.next(), prefix, matchingResult);
      }

      return matchingResult;
    }

    private List<String> prefixRecursion(Map.Entry<Character, TrieNode> entry, String word, List<String> words){

      if(entry == null){
        return words;
      }

      word += entry.getKey();
      if(entry.getValue().isEnd ){
        words.add(word);
        return words;
      }

      Map<Character, TrieNode> links = entry.getValue().links;
      Set<Map.Entry<Character, TrieNode>> entrySet = links.entrySet();
      Iterator<Map.Entry<Character, TrieNode>> iterator = entrySet.iterator();
      while (iterator.hasNext()){
        prefixRecursion(iterator.next(), word, words);
      }

      return words;
    }
  }

  public static void main(String[] args) {
    String[] input = new String[]{"dog", "deer", "deep", "deal", "death"};

    Trie trie = new Trie();
    for(String word: input){
      trie.insert(word);
    }

    String searchWord = "de";
    List<String> matchingWords = trie.prefixSearch(searchWord);
    System.out.println(Arrays.toString(matchingWords.toArray()));

    searchWord = "de";
    matchingWords = trie.prefixSearch(searchWord);
    System.out.println(Arrays.toString(matchingWords.toArray()));

    searchWord = "dea";
    matchingWords = trie.prefixSearch(searchWord);
    System.out.println(Arrays.toString(matchingWords.toArray()));

    searchWord = "deal";
    matchingWords = trie.prefixSearch(searchWord);
    System.out.println(Arrays.toString(matchingWords.toArray()));

  }
}
