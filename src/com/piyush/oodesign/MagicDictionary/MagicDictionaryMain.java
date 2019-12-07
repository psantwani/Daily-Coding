package com.piyush.oodesign.MagicDictionary;

import java.util.HashMap;
import java.util.HashSet;

public class MagicDictionaryMain {

  public static void main(String ... args){

    String[] dict = new String[]{"daily", "daisy", "coding", "practice", "object", "oriented", "programming"};
    MagicDictionary magicDictionary = new MagicDictionary(dict);
    System.out.println(magicDictionary.search("dagly"));
    System.out.println(magicDictionary.search("doding"));
    System.out.println(magicDictionary.search("dading"));
    System.out.println(magicDictionary.search("dodings"));
    System.out.println(magicDictionary.search("practici"));
    System.out.println(magicDictionary.search("orientes"));
  }

}

class MagicDictionary{

  private Trie trie;

  public MagicDictionary(String[] dict){
    trie = new Trie();
    for(String word : dict){
      trie.insert(word);
    }
  }

  public boolean search(String word){
    return dfs(word, 0, 0, trie.root);
  }

  private boolean dfs(String word, int level, int editDistance, Trie.TrieNode node){

    if(editDistance > 1){
      return false;
    }

    if(level == word.length() && node.isEndOfWord && editDistance == 1){
      return true;
    }

    if(level == word.length()){
      return false;
    }

    int index = word.charAt(level) - 'a';

    for(int i = 0; i < 26; i++){
      Trie.TrieNode temp = node.children[i];
      if(temp == null){
        continue;
      }

      boolean magicWordFound = false;
      if(i == index){
        magicWordFound = dfs(word, level + 1, editDistance, node.children[i]);
      } else {
        magicWordFound = dfs(word, level + 1, editDistance + 1, node.children[i]);
      }

      if(magicWordFound){
        return true;
      }

    }

    return false;
  }

}

class Trie {

  // Alphabet size (# of symbols)
  final int ALPHABET_SIZE = 26;

  // trie node
  class TrieNode {
    TrieNode[] children = new TrieNode[ALPHABET_SIZE];

    // isEndOfWord is true if the node represents
    // end of a word
    boolean isEndOfWord;

    TrieNode() {
      isEndOfWord = false;
      for (int i = 0; i < ALPHABET_SIZE; i++)
        children[i] = null;
    }
  }

  TrieNode root;

  public Trie(){
    root = new TrieNode();
  }

  // If not present, inserts key into trie
  // If the key is prefix of trie node,
  // just marks leaf node
  public void insert(String key) {
    int level;
    int length = key.length();
    int index;

    TrieNode pCrawl = root;

    for (level = 0; level < length; level++) {
      index = key.charAt(level) - 'a';

      if (pCrawl.children[index] == null)
        pCrawl.children[index] = new TrieNode();

      pCrawl = pCrawl.children[index];
    }

    // mark last node as leaf
    pCrawl.isEndOfWord = true;
  }

  // Returns true if key presents in trie, else false
  public boolean search(String key) {
    int level;
    int length = key.length();
    int index;
    TrieNode pCrawl = root;

    for (level = 0; level < length; level++) {
      index = key.charAt(level) - 'a';

      if (pCrawl.children[index] == null)
        return false;

      pCrawl = pCrawl.children[index];
    }

    return (pCrawl != null && pCrawl.isEndOfWord);
  }

}

