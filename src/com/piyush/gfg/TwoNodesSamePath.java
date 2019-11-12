package com.piyush.gfg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a tree (not necessarily a binary tree) and a number of queries such that every query
 * takes two nodes of tree as parameters. For every query pair,
 * find if two nodes are on the same path from root to the bottom.
 */
public class TwoNodesSamePath {

  static class Graph {
    int V;
    LinkedList<Integer>[] adjList;

    public Graph(int V) {
      this.V = V;
      this.adjList = new LinkedList[V];

      for (int i = 0; i < V; i++) {
        this.adjList[i] = new LinkedList<>();
      }
    }

    public void addEdge(int src, int dest) {
      this.adjList[src].add(dest);
    }

  }

  public static boolean solution(Graph graph, int one, int two){
    boolean[] visited = new boolean[graph.V];
    LinkedList<Integer> first = graph.adjList[0];
    for(int i = 0; i < first.size(); i++){
      List<Integer> pairsFound = new ArrayList<>();
      if(one == 0 || two == 0){
        pairsFound.add(i);
      }
      if(samePathUtil(graph, graph.adjList[0].get(i), one, two, pairsFound, visited)){
        return true;
      }
    }

    return false;
  }

  private static boolean samePathUtil(Graph graph, int curr, int one, int two, List<Integer> pairsFound, boolean[] visited){

    if(curr == one || curr == two){
      pairsFound.add(curr);
    }

    Iterator it = graph.adjList[curr].iterator();
    while (it.hasNext()){
      int neighbour = (int) it.next();

      if(visited[neighbour]){
        continue;
      }

      visited[neighbour] = true;

      if(neighbour == one || neighbour == two){
        pairsFound.add(neighbour);
      }

      if(pairsFound.size() == 2){
        return true;
      }

      return samePathUtil(graph, neighbour, one, two, pairsFound, visited);
    }

    return false;
  }

  public static void main(String[] args){
    Graph graph = new Graph(8);
    graph.addEdge(0,1);
    graph.addEdge(0,2);
    graph.addEdge(0,3);
    graph.addEdge(1,4);
    graph.addEdge(2,5);
    graph.addEdge(3,6);
    graph.addEdge(4,7);
    System.out.println(solution(graph, 0,4));
    System.out.println(solution(graph, 0,5));
    System.out.println(solution(graph, 1,5));
    System.out.println(solution(graph, 1,7));
  }
}
