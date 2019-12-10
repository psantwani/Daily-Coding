package com.piyush.geeksforgeeks;

import java.util.Arrays;

public class Kruskal {

  static class Graph {
    // A class to represent a graph edge
    class Edge implements Comparable<Edge> {
      int src, dest, weight;

      // Comparator function used for sorting edges
      // based on their weight
      public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight;
      }
    }

    ;

    int V, E;    // V-> no. of vertices & E->no.of edges
    Edge edge[]; // collection of all edges

    // Creates a graph with V vertices and E edges
    Graph(int v, int e) {
      V = v;
      E = e;
      edge = new Edge[E];
      for (int i = 0; i < e; ++i)
        edge[i] = new Edge();
    }


    // The main function to construct MST using Kruskal's algorithm
    void KruskalMST() {
      Edge[] result = new Edge[V];
      int e = 0;
      int[] parent = new int[V];
      for(int i = 0; i < V; i++){
        parent[i] = -1;
      }

      Arrays.sort(edge);

      for(int i = 0; i < E; i++){
        Edge edge = this.edge[i];
        int x = find(parent, edge.src);
        int y = find(parent, edge.dest);

        if(x != y){
          result[e++] = edge;
          union(parent, x, y);
        }
      }

      System.out.println("Following are the edges in " +
          "the constructed MST");
      for (int i = 0; i < e; ++i)
        System.out.println(result[i].src+" -- " +
            result[i].dest+" == " + result[i].weight);
    }

    int find(int[] parent, int i){
      if(parent[i] == -1){
        return i;
      }

      return find(parent, parent[i]);
    }

    void union(int[] parent, int x, int y){
      int xset = find(parent, x);
      int yset = find(parent, y);

      if(xset != yset){
        parent[xset] = yset;
      }
    }

    public static void main(String[] args) {

        /* Let us create following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */
      int V = 4;  // Number of vertices in graph
      int E = 5;  // Number of edges in graph
      Graph graph = new Graph(V, E);

      // add edge 0-1
      graph.edge[0].src = 0;
      graph.edge[0].dest = 1;
      graph.edge[0].weight = 10;

      // add edge 0-2
      graph.edge[1].src = 0;
      graph.edge[1].dest = 2;
      graph.edge[1].weight = 6;

      // add edge 0-3
      graph.edge[2].src = 0;
      graph.edge[2].dest = 3;
      graph.edge[2].weight = 5;

      // add edge 1-3
      graph.edge[3].src = 1;
      graph.edge[3].dest = 3;
      graph.edge[3].weight = 15;

      // add edge 2-3
      graph.edge[4].src = 2;
      graph.edge[4].dest = 3;
      graph.edge[4].weight = 4;

      graph.KruskalMST();
    }

  }
}
