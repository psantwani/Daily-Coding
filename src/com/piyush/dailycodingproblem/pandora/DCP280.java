package com.piyush.dailycodingproblem.pandora;

/*
This problem was asked by Pandora.

Given an undirected graph, determine if it contains a cycle.
 */

public class DCP280 {

  static class Edge{
    int src, dest;
  }

  static class Graph{
    int V, E;
    Edge[] edge;

    public Graph(int V, int E){
      this.V = V;
      this.E = E;
      edge = new Edge[E];
      for(int i = 0; i < E; i++){
        edge[i] = new Edge();
      }
    }

    public boolean isCyclic(){
      int[] parent = new int[V];

      for(int i = 0 ; i < V; i++){
        parent[i] = -1;
      }

      for(int i = 0; i < edge.length; i++){
        int x = find(edge[i].src, parent);
        int y = find(edge[i].dest, parent);

        if(x == y){
          return true;
        }

        union(x, y, parent);
      }

      return false;

    }

    public int find(int i, int[] parent){
      if(parent[i] == -1){
        return i;
      }

      return find(parent[i], parent);
    }

    public void union(int x, int y, int[] parent){
      int xset = find(x, parent);
      int yset = find(y, parent);
      parent[xset] = yset;
    }
  }

  public static void main(String ... args){
    int V = 3, E = 3;
    Graph graph = new Graph(V, E);

    // add edge 0-1
    graph.edge[0].src = 0;
    graph.edge[0].dest = 1;

    // add edge 1-2
    graph.edge[1].src = 1;
    graph.edge[1].dest = 2;

    // add edge 0-2
    graph.edge[2].src = 0;
    graph.edge[2].dest = 2;

    if (graph.isCyclic())
      System.out.println( "graph contains cycle" );
    else
      System.out.println( "graph doesn't contain cycle" );
  }

}
