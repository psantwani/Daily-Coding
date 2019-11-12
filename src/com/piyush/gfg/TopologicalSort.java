package com.piyush.gfg;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class TopologicalSort {

  static class Graph{
    int V;
    LinkedList<Integer>[] adjList;

    public Graph(int V){
      this.V = V;
      this.adjList = new LinkedList[V];

      for(int i = 0; i < V; i++){
        this.adjList[i] = new LinkedList<>();
      }
    }

    public void addEdge(int src, int dest){
      this.adjList[src].add(dest);
    }

    /**
     * Uses DFS.
     */
    public void topologicalSort(){
      boolean[] visited = new boolean[V];
      Stack<Integer> stack = new Stack<>();

      for(int i = 0; i < V; i++){
        if(!visited[i]){
          topologicalSortUtil(i, visited, stack);
        }
      }

      StringBuilder output = new StringBuilder();
      while (!stack.isEmpty()){
        output.insert(0, stack.pop());
      }

      System.out.println("Topological Sorted: " + output);
    }

    private void topologicalSortUtil(int src, boolean[] visited, Stack<Integer> stack){

      visited[src] = true;
      Iterator it = adjList[src].iterator();

      while (it.hasNext()){
        int neighbour = (int) it.next();
        if(visited[neighbour]){
          continue;
        }

        topologicalSortUtil(neighbour, visited, stack);
      }

      stack.push(src);
    }
  }

  public static void main(String[] args){
    Graph graph = new Graph(5);
    graph.addEdge(0, 1);
    graph.addEdge(0, 2);
    graph.addEdge(1, 3);
    graph.addEdge(2,3);
    graph.addEdge(3,4);
    graph.topologicalSort();
  }

}
