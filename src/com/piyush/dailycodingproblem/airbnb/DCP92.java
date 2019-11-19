package com.piyush.dailycodingproblem.airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * This problem was asked by Airbnb.
 *
 * We're given a hashmap associating each courseId key with a
 * list of courseIds values, which represents that the prerequisites of courseId are courseIds.
 * Return a sorted ordering of courses such that we can finish all courses.
 *
 * Return null if there is no such ordering.
 *
 * For example, given {
 * 'CSC300': ['CSC100', 'CSC200'],
 * 'CSC200': ['CSC100'],
 * 'CSC100': []},
 *
 * should return ['CSC100', 'CSC200', 'CSCS300'].
 */
public class DCP92 {

  public static List<String> solution(Map<String, String[]> map){
	Set<String> visited = new HashSet<>();
	Stack<String> stack = new Stack<>();

	for(Map.Entry<String, String[]> entry : map.entrySet()){
	  String key = entry.getKey();

	  if(visited.contains(key)){
	    continue;
	  }

	  Set<String> currDfsVisited = new HashSet<>();

	  try {
		dfs(key, map, currDfsVisited, visited, stack);
	  } catch (Exception e){
	    System.out.println("null");
	  	return null;
	  }

	  visited.add(key);
	  stack.push(key);
	}

	List<String> result = new ArrayList<>();
	for(String item : stack){
	  result.add(item);
	}

	System.out.println(Arrays.toString(result.toArray()));
	return result;
  }

  private static void dfs(String key, Map<String, String[]> map, Set<String> currDfsVisited, Set<String> visited, Stack<String> stack){
    currDfsVisited.add(key);

    String[] value = map.get(key);
	for(int i = 0; i < value.length; i++){
	  String dependent = value[i];

	  if(visited.contains(dependent)){
		continue;
	  }

	  if(currDfsVisited.contains(dependent)){
	    throw new IllegalArgumentException("Loop detected. Ordering not possible");
	  }

	  currDfsVisited.add(dependent);
	  dfs(dependent, map, currDfsVisited, visited, stack);
	  currDfsVisited = new HashSet<>();
	  visited.add(dependent);
	  stack.push(dependent);
	}
  }

  public static void main(String[] args){
	Map<String, String[]> map = new HashMap<>();
	map.put("CSC100", new String[]{});
	map.put("CSC200", new String[]{"CSC100"});
	map.put("CSC300", new String[]{"CSC100", "CSC200"});
	solution(map);

	map.put("CSC100", new String[]{"CSC400"});
	map.put("CSC200", new String[]{"CSC100", "CSC400"});
	map.put("CSC300", new String[]{"CSC200"});
	map.put("CSC400", new String[]{"CSC100"});
	solution(map);

	map.put("CSC100", new String[]{"CSC200", "CSC400"});
	map.put("CSC200", new String[]{"CSC300", "CSC400"});
	map.put("CSC300", new String[]{});
	map.put("CSC400", new String[]{"CSC300"});
	solution(map);
  }
}
