package com.piyush.dailycodingproblem.facebook;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * A graph is minimally-connected if it is connected and there is no edge that can be removed
 * while still leaving the graph connected. For example, any binary tree is minimally-connected.
 *
 * Given an undirected graph, check if the graph is minimally-connected.
 * You can choose to represent the graph as either an adjacency matrix or adjacency list.
 */
public class DCP182 {

  public static boolean solution(Graph graph){
    boolean[] visited = new boolean[graph.V];

    for(int i = 0; i < graph.V; i++){
      if(!visited[i]){
        if(!isMinimallyConnected(graph, i, visited, -1)){
          return false;
        }
      }
    }

    return true;
  }

  // In other words, is not cyclic.
  private static boolean isMinimallyConnected(Graph graph, int curr, boolean[] visited, int parent){

    visited[curr] = true;

    LinkedList<Integer> neighbours = graph.edges[curr];
    Iterator it = neighbours.iterator();

    while (it.hasNext()){
      int neighbour = (int) it.next();

      if(!visited[neighbour]){
        if(!isMinimallyConnected(graph, neighbour, visited, curr)){
          return false;
        }
      }
      else if(neighbour != parent){
        // implying there is a cycle.
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args){
    Graph graph = new Graph(5);
    graph.addEdge(2,1);
    graph.addEdge(1,0);
    graph.addEdge(3,0);
    graph.addEdge(4,3);
    System.out.println(solution(graph));

    Graph graph2 = new Graph(5);
    graph2.addEdge(1,2);
    graph2.addEdge(1,0);
    graph2.addEdge(0,2);
    graph2.addEdge(0,3);
    graph2.addEdge(3,4);
    System.out.println(solution(graph2));
  }

  static class Graph{
    int V;
    LinkedList<Integer>[] edges;

    Graph(int V){
      this.V = V;
      edges = new LinkedList[V];
      for(int i = 0; i < V ; i++){
        edges[i] = new LinkedList<>();
      }
    }

    void addEdge(int src, int dest){
      edges[src].add(dest);
      edges[dest].add(src);
    }
  }

}
