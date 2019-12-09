package com.piyush.geeksforgeeks;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class AlienDictionary {

  static class Graph {
    public int V;
    public LinkedList<Integer>[] edges;

    public Graph(int V){
      this.V = V;
      edges = new LinkedList[V];
      for(int i = 0; i < V; i++){
        edges[i] = new LinkedList<Integer>();
	  }
	}

	public void addEdge(int u, int v){
      edges[u].add(v);
	}

  }



  static void printOrder(String[] words, int N){

    Graph graph = new Graph(N);

    for(int i = 0; i < N - 1; i++){

      String word1 = words[i];
      String word2 = words[i+1];

      for(int j = 0; j < Math.min(word1.length(), word2.length()); j++){
        int char1 = word1.charAt(j);
        int char2 = word2.charAt(j);

        if(char1 != char2){
          graph.addEdge(char1 - 'a', char2 - 'a');
          break;
		}
	  }
	}

	Stack<Integer> result = topologicalSort(graph);

    while (!result.isEmpty()){
      System.out.print((char)('a' + result.pop()) + " ");
	}
  }

  private static Stack<Integer> topologicalSort(Graph graph){
    Set<Integer> visited = new HashSet<>();
	Stack<Integer> stack = new Stack<>();

	for(int i = 0; i < graph.V; i++){
	  if(!visited.contains(i)){
	    topologicalSortUtil(graph, i, visited, stack);
	  }
	}

	return stack;
  }

  private static void topologicalSortUtil(Graph graph, Integer curr, Set<Integer> visited, Stack<Integer> stack){
    visited.add(curr);
	LinkedList<Integer> currList = graph.edges[curr];
	for (Integer next : currList) {
	  if(!visited.contains(next)){
	    topologicalSortUtil(graph, next, visited, stack);
	  }
	}

	stack.push(curr);
  }

  public static void main(String[] args)
  {
	String[] words = {"caa", "aaa", "aab"};
	printOrder(words, 3);
  }
}
