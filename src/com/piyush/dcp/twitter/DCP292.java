package com.piyush.dcp.twitter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * This problem was asked by Twitter.
 *
 * A teacher must divide a class of students into two teams to play dodgeball. Unfortunately, not all the kids get along, and several refuse to be put on the same team as that of their enemies.
 *
 * Given an adjacency list of students and their enemies, write an algorithm that finds a satisfactory pair of teams, or returns False if none exists.
 *
 * For example, given the following enemy graph you should return the teams {0, 1, 4, 5} and {2, 3}.
 *
 * students = {
 *     0: [3],
 *     1: [2],
 *     2: [1, 4],
 *     3: [0, 4, 5],
 *     4: [2, 3],
 *     5: [3]
 * }
 * On the other hand, given the input below, you should return False.
 *
 * students = {
 *     0: [3],
 *     1: [2],
 *     2: [1, 3, 4],
 *     3: [0, 2, 4, 5],
 *     4: [2, 3],
 *     5: [3]
 * }
 */
public class DCP292 {


  /**
   * Approach : Check if the given graph is a bipartite.
   */
  public static String solution(Graph G){
    int[] colors = new int[G.V]; // colors of each vertex
    for(int i = 0; i < G.V; i++){
      colors[i] = -1; // -1 represents no color, 0 is color A and 1 is color B.
    }

    colors[0] = 1; // assigning color A to 0th vertex.

    Queue<Integer> queue = new LinkedList();
    queue.add(0);

    while (!queue.isEmpty()){
      int curr = queue.poll();
      int[] enemies = G.edges[curr];

      for (int enemy : enemies) {
        if (colors[enemy] == -1) {
          colors[enemy] = 1 - colors[curr];
          queue.add(enemy);
        } else if (colors[enemy] == colors[curr]) {
          System.out.println("False");
          return "False";
        }
      }
    }

    StringBuilder teamA = new StringBuilder();
    StringBuilder teamB = new StringBuilder();

    for(int i = 0; i < colors.length; i++){
      int color = colors[i];
      if(color == 1){
        teamA.append(i).append(" ");
      } else if(color == 0){
        teamB.append(i).append(" ");
      }
    }

    String result = "Team A: " + teamA.toString().trim() + " | Team B: " + teamB.toString().trim();
    System.out.println(result);
    return result;
  }

  public static void main(String[] args){

    Graph G = new Graph(6);
    G.addEdge(0, new int[]{3});
    G.addEdge(1, new int[]{2});
    G.addEdge(2, new int[]{1,4});
    G.addEdge(3, new int[]{0,4,5});
    G.addEdge(4, new int[]{2,3});
    G.addEdge(5, new int[]{3});
    solution(G);

    Graph G2 = new Graph(6);
    G2.addEdge(0, new int[]{3});
    G2.addEdge(1, new int[]{2});
    G2.addEdge(2, new int[]{1,3,4});
    G2.addEdge(3, new int[]{0,2,4,5});
    G2.addEdge(4, new int[]{2,3});
    G2.addEdge(5, new int[]{3});
    solution(G2);
  }

  static class Graph{
    int V;
    int[][] edges;

    Graph(int V){
      this.V = V;
      edges = new int[V][];
    }

    void addEdge(int src, int[] dest){
      for(int i = 0; i < V; i++){
        edges[src] = dest;
      }
    }
  }
}
