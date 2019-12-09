package com.piyush.geeksforgeeks;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class ShortestPath {


  static class Node{
    int id;
    int dist;

    public Node(int id, int dist){
		this.id = id;
		this.dist = dist;
	}
  }

  static void dijkstra(int[][] graph, int vertex){
    int N = graph.length;

	HashMap<Integer, Integer> shortestPath = new HashMap<>();
	shortestPath.put(0, 0);
	HashSet<Integer> visited = new HashSet<>();
	PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
	  return a.dist - b.dist;
	});

	util(graph, new Node(0,0), visited, pq, shortestPath);

	for(Map.Entry<Integer, Integer> entry : shortestPath.entrySet()){
	  System.out.println("0 -> " + entry.getKey() + " : " + entry.getValue());
	}
  }

  private static void util(int[][] graph, Node vertex, HashSet<Integer> visited, PriorityQueue<Node> pq, HashMap<Integer, Integer> shortestPath){

    if(visited.size() == graph.length){
      return;
	}

    for(int i = 0; i < graph.length; i++){
      int currWeight = shortestPath.getOrDefault(i, Integer.MAX_VALUE);
      if(graph[vertex.id][i] != 0){
		int newWeight = vertex.dist + graph[vertex.id][i];
		if(newWeight < currWeight){
		  graph[vertex.id][i] = newWeight;
		  shortestPath.put(i, newWeight);
		}
		if(!visited.contains(i)){
		  pq.add(new Node(i, newWeight));
		}
	  }
	}

	visited.add(vertex.id);
    util(graph, pq.poll(), visited, pq, shortestPath);
  }

  public static void main(String args[])
  {
	/* Let us create the example graph discussed above */
	int graph[][] = new int[][] {
		{ 0, 4, 0, 0, 0, 0, 0, 8, 0 },
		{ 4, 0, 8, 0, 0, 0, 0, 11, 0 },
		{ 0, 8, 0, 7, 0, 4, 0, 0, 2 },
		{ 0, 0, 7, 0, 9, 14, 0, 0, 0 },
		{ 0, 0, 0, 9, 0, 10, 0, 0, 0 },
		{ 0, 0, 4, 14, 10, 0, 2, 0, 0 },
		{ 0, 0, 0, 0, 0, 2, 0, 1, 6 },
		{ 8, 11, 0, 0, 0, 0, 1, 0, 7 },
		{ 0, 0, 2, 0, 0, 0, 6, 7, 0 }
	};
	dijkstra(graph, 0);
  }

}
