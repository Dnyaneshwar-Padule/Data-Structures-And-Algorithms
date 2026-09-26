package com.tca.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

/**
 * Undirected Graph implementation with adjacency matrix
 */
public class Graph {

	private final int V;
	private int E;
	private boolean[][] adjacencyMatrix;
	
	public Graph(int V) {
		if(V < 1)
			throw new IllegalArgumentException("Too few vertices.");
		this.V = V;
		adjacencyMatrix = new boolean[V][V];
	}
	
	public Graph(int V, int E) {
		this(V);
		
		if( E < V - 1 )
			throw new IllegalArgumentException("Too few edges.");
		if( E > (V * (V - 1)) / 2 )
			throw new IllegalArgumentException("Too many edges.");		
		populateRandomGraph(E);
	}
	
	private void populateRandomGraph(int E) {
		Random random = new Random();
		for(int i = 0 ; i < E ; i++) {
			int u = random.nextInt(V);
			int v = random.nextInt(V);
			addEdge(u, v);
		}
	}
	
	/**
	 * Add an edge in undirected graph.
	 * @param u
	 * @param v
	 */
	public void addEdge(int u, int v) {
		if(u < 0 || u >= V)
			throw new IllegalArgumentException("Invalid vertex u:" + u);
		if(V < 0 || v >= V)
			throw new IllegalArgumentException("Invalid vertex v:" + u);
		adjacencyMatrix[u][v] = true;
		adjacencyMatrix[v][u] = true;
		E++;
	}
	
	public boolean contains(int u, int v) {
		return adjacencyMatrix[u][v];
	}
	
	/**
	 * Iterator to iterate to all adjacent vertices from u
	 * @param u
	 * @return Iterator<Boolean>
	 * @throws IllegalArgumentException
	 */
	public Iterator<Integer> iterator(int u){
		if(u < 0 || u >= V)
				throw new IllegalArgumentException("Invalid vertex u:" + u);
		return new GIterator(u);
	}
	
	private class GIterator implements Iterator<Integer>, Iterable<Integer>{
		int u;
		int v;
		
		public GIterator(int u) {
			this.u = u;
		}
			
		@Override
		public Iterator<Integer> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			while(v < V) {
				if(adjacencyMatrix[u][v]) return true;
				v++;
			}
			return false;
		}

		@Override
		public Integer next() {
			if(!hasNext())
				throw new NoSuchElementException();
			return v++;
		}
	}
	
	
	
	/*
	 		  0  1  2  3 
	 		0 1  0  1  0  
	 		1 
	 		2
	 		3
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		/* header vertices / column */
		sb.append(" ");
		for(int i = 0; i < V; i++)
			sb.append("  " + i);
		sb.append("\n"); // new line
		
		for(int i = 0; i < V; i++) {
			sb.append(i);  // row start
			
			for(int j = 0; j < V; j++) {
				if(adjacencyMatrix[i][j])
					sb.append("  " + 1);
				else
					sb.append("  " + 0);
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	
	public List<Integer> dfs(){
		List<Integer> vertices = new ArrayList<>();
		Stack<Integer> s = new Stack<>();
		boolean[] visited = new boolean[V];
		s.push(0);
		
		while(! s.isEmpty() ) {
			int u = s.pop();
			
			if(!visited[u]) {
				vertices.add(u);
				visited[u] = true;
			}
			
			for(int v = 0; v < V; v++) {
				if(!visited[v] && adjacencyMatrix[u][v])
					s.push(v);
			}
		}
		return vertices;
	}
	
	public List<Integer> bfs() {
		List<Integer> vertices = new ArrayList<>();
		Queue<Integer> q = new LinkedList<Integer>();
		boolean visited[] = new boolean[V];
		q.offer(0);
		
		while(! q.isEmpty() ) {
			int u = q.poll();
			
			if(!visited[u]) {
				vertices.add(u);
				visited[u] = true;
			}
			
			for(int v = 0; v < V; v++) {
				if(!visited[v] && adjacencyMatrix[u][v])
					q.offer(v);
			}
		}
		
		return vertices;
	}
}
