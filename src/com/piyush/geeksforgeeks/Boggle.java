package com.piyush.geeksforgeeks;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Boggle {

  static class TrieNode{
    private final int N = 26;
    private TrieNode[] links;
    private boolean isEnd;

    public TrieNode(){
      this.links = new TrieNode[N];
    }

  }

  static class Trie{
    TrieNode root;

    public Trie(){
      this.root = new TrieNode();
    }

    public void add(String word){
      TrieNode node = this.root;
      for(int i = 0; i < word.length(); i++){
        char letter = word.charAt(i);
        letter = Character.toLowerCase(letter);
        if(node.links[letter - 'a'] == null){
          node.links[letter - 'a'] = new TrieNode();
        }
        node = node.links[letter - 'a'];
      }
      if(word.length() > 0){
        node.isEnd = true;
      }
    }

    public int isPrefixOrWord(String word){
      TrieNode node = root;
      for(int i = 0; i < word.length(); i++){
        char letter = word.charAt(i);
        letter = Character.toLowerCase(letter);
        if(node.links[letter - 'a'] == null){
          return 0;
        }
        node = node.links[letter - 'a'];
      }

      if(node.isEnd){
        return 2;
      } else {
        return 1;
      }
    }
  }

  public static String[] solution(char[][] boggle, String[] dict){
    List<String> result = new ArrayList<>();
    Trie trie = new Trie();
    for(int i = 0; i < dict.length; i++){
      trie.add(dict[i]);
    }

    for(int i = 0; i < boggle.length; i++){
      for(int j = 0; j < boggle[0].length; j++){
        Set<String> visited = new HashSet<>();
        List<String> temp = dfs(i, j , boggle, visited, trie.root, new StringBuilder(), new ArrayList<String>());
        if(temp != null && temp.size() > 0){
          result.addAll(temp);
        }
      }
    }


    return (String[]) result.toArray();
  }

  public static List<String> dfs(int i, int j, char[][] boggle, Set<String> set, TrieNode node, StringBuilder sb, List<String> matches){

    if(i < 0 || i > 2 || j < 0 || j > 2){
      sb.append('#');
      return null;
    }

    char letter = boggle[i][j];
    sb.append(letter);

    letter = Character.toLowerCase(letter);
    TrieNode nextTrieNode = node.links[letter - 'a'];

    if(nextTrieNode == null){
      return null;
    }

    String cell = "r:" + i + ";c" + j;
    if(set.contains(cell)){
      return matches;
    }

    set.add(cell);

    if(nextTrieNode.isEnd){
      matches.add(sb.toString());
    }

    dfs(i-1, j-1, boggle, set, nextTrieNode, sb, matches);
    clean(sb, set, cell);
    dfs(i-1, j, boggle, set, nextTrieNode, sb, matches);
    clean(sb, set, cell);
    dfs(i-1, j+1, boggle, set, nextTrieNode, sb, matches);
    clean(sb, set, cell);
    dfs(i, j-1, boggle, set, nextTrieNode, sb, matches);
    clean(sb, set, cell);
    dfs(i, j+1, boggle, set, nextTrieNode, sb, matches);
    clean(sb, set, cell);
    dfs(i+1, j-1, boggle, set, nextTrieNode, sb, matches);
    clean(sb, set, cell);
    dfs(i+1, j, boggle, set, nextTrieNode, sb, matches);
    clean(sb, set, cell);
    dfs(i+1, j+1, boggle, set, nextTrieNode, sb, matches);
    clean(sb, set, cell);

    return matches;
  }

  public static void clean(StringBuilder sb, Set<String> set, String cell){
    sb.deleteCharAt(sb.length() - 1);
    set.remove(cell);
  }

  public static void main(String ... args){
    char boogle[][] = new char[3][3];
    boogle[0] = new char[]{'G', 'I', 'Z'};
    boogle[1] = new char[]{'U', 'E', 'K'};
    boogle[2] = new char[]{'Q', 'S', 'E'};

    String[] dict = new String[]{"GEEKS", "FOR", "QUIZ", "GO", "SEE", "SEEK"};

    solution(boogle, dict);
  }
}
