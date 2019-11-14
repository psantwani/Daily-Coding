package com.piyush.dailycodingproblem.samsung;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * This problem was asked by Samsung.
 * <p>
 * A group of houses is connected to the main water plant by means of a set of pipes. A house can either be connected by a set of pipes extending directly to the plant, or indirectly by a pipe to a nearby house which is otherwise connected.
 * <p>
 * For example, here is a possible configuration, where A, B, and C are houses, and arrows represent pipes:
 * <p>
 * A <--> B <--> C <--> plant
 * Each pipe has an associated cost, which the utility company would like to minimize. Given an undirected graph of pipe connections, return the lowest cost configuration of pipes such that each house has access to water.
 * <p>
 * In the following setup, for example, we can remove all but the pipes from plant to A, plant to B, and B to C, for a total cost of 16.
 * <p>
 * pipes = {
 * 'plant': {'A': 1, 'B': 5, 'C': 20},
 * 'A': {'C': 15},
 * 'B': {'C': 10},
 * 'C': {}
 * }
 */
public class DCP299 {

  static Map<Integer, Character> map;

  public static int solution(Graph G) {
    int minCost = 0;
    PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> {
      return a.weight - b.weight;
    });

    for (Edge edge : G.edges) {
      pq.add(edge);
    }

    int[] parent = new int[G.noOfVertices];

    for (int i = 0; i < G.noOfVertices; i++) {
      parent[i] = -1; // initializing all to -1, implying the the parent index is the parent of only itself. We couldn't do
      // with initializing with 0 because that would imply that the 0th index is the parent of all the indexes.
    }

    List<Edge> mst = new ArrayList<>();

    while (!pq.isEmpty()) {
      Edge edge = pq.peek();
      int x = find(parent, edge.src);
      int y = find(parent, edge.dest);

      if (x == y) {
        pq.poll();
        continue;
      }

      union(parent, x, y);
      mst.add(edge);
      pq.poll();
    }

    for (Edge e : mst) {
      minCost += e.weight;
    }

    System.out.println("Minimum cost: " + minCost);

    return minCost;
  }

  static int find(int[] parent, int i) {
    if (parent[i] == -1) {
      return i; // if the passed index is only the parent of itself, then return the index.
    }

    return find(parent, parent[i]); // else follow the parent index till we get the index which only points to itself.

    // to improve efficiency, set parent[i] = find(parent, parent[i]) for faster read next time. This is called compression.
  }

  static void union(int[] parent, int x, int y) {
    int xset = find(parent, x);
    int yset = find(parent, y);
    if (xset != yset) {
      parent[xset] = yset;
    }
  }

  public static void main(String[] args) {
    map = new HashMap<>();
    map.put(0, 'P');
    map.put(1, 'A');
    map.put(2, 'B');
    map.put(3, 'C');

    Graph graph = new Graph(4);

    graph.addEdge(0, 1, 1);
    graph.addEdge(0, 2, 5);
    graph.addEdge(0, 3, 20);

    graph.addEdge(1, 3, 20);
    graph.addEdge(2, 3, 10);

    solution(graph);
  }

  static class Edge {
    int src;
    int dest;
    char srcTag;
    char destTag;
    int weight;

    public Edge(int src, int dest, int weight) {
      this.src = src;
      this.dest = dest;
      this.srcTag = map.get(src);
      this.destTag = map.get(dest);
      this.weight = weight;
    }
  }


  static class Graph {
    public static int noOfVertices;
    public static ArrayList<Edge> edges;

    Graph(int noOfVertices) {
      this.noOfVertices = noOfVertices;
      edges = new ArrayList<>();
    }

    public void addEdge(int src, int dest, int weight) {
      edges.add(new Edge(src, dest, weight));
    }


  }

}