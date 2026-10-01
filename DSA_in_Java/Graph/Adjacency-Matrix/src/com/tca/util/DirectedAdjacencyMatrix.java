package com.tca.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class DirectedAdjacencyMatrix {

	private final int V;
	private int E;
	private boolean[][] adjacencyMatrix;
	
	public DirectedAdjacencyMatrix(int V) {
		if(V < 1) throw new IllegalArgumentException("Too few vertices v:" + V);
		this.V = V;
		adjacencyMatrix = new boolean[V][V];
	}
	
	public DirectedAdjacencyMatrix(int V, int E) {
		this(V);
		if(E < V-1) throw new IllegalArgumentException("Too few edges. E:" + E);
		if( E > (V * (V-1))) throw new IllegalArgumentException("Too many edges. E:" + E);
		populateRandomGraph(E);
	}
	
	private void populateRandomGraph(int E) {
		Random random = new Random();
		while(E != this.E) {
			int u = random.nextInt(V);
			int v  = random.nextInt(V);
			add(u, v);
		}
	}
	
	
	public int E() {
		return E;
	}
	
	public int V() {
		return V;
	}
	
	private boolean validateVertex(int v) {
		return (v >= 0 && v < V);
	}
	
	public boolean contains(int u, int v) {
		if(!validateVertex(u)) throw new IllegalArgumentException("Invalid vertex u:" + u);
		if(!validateVertex(v)) throw new IllegalArgumentException("Invalid vertex v:" + v);
		return adjacencyMatrix[u][v];
	}
	
	public void add(int u, int v) {
		if(!validateVertex(u)) throw new IllegalArgumentException("Invalid vertex u:" + u);
		if(!validateVertex(v)) throw new IllegalArgumentException("Invalid vertex v:" + v);
		if(v == u || adjacencyMatrix[u][v]) return;  // no self edge (requirement)
		adjacencyMatrix[u][v] = true;
		E++;
	}
	
	public int indegreeOf(int v) {
		if(!validateVertex(v)) throw new IllegalArgumentException("Invalid vertex v:" + v);
		int inDegree = 0;
		for(int u = 0; u < V; u++) {
			if( adjacencyMatrix[u][v] )
				inDegree++;
		}
		return inDegree;
	}
	
	public int outdegreeOf(int u) {
		if(!validateVertex(u)) throw new IllegalArgumentException("Invalid vertex u:" + u);
		int outDegree = 0;
		for(int v = 0; v < V; v++) {
			if(adjacencyMatrix[u][v])
				outDegree++;
		}
		return outDegree;
	}
	
	public Iterable<Integer> iterator(int u){
		return new DirectedAdjacencyListIterator(u);
	}
	
	private class DirectedAdjacencyListIterator implements Iterable<Integer>, Iterator<Integer>{
		int u;
		int v;
		
		public DirectedAdjacencyListIterator(int u) {
			if(!validateVertex(u)) throw new IllegalArgumentException("Invalid vertex u:" + u);
			this.u = u;
			v = 0;
		}
		
		@Override
		public boolean hasNext() {
			while(v < V) {
				if(adjacencyMatrix[u][v])
					return true;
				v++;
			}
			return false;
		}

		@Override
		public Integer next() {
			if(!hasNext()) throw new NoSuchElementException("No more edges available, current state u:" + u + ", v:" + v);
			return v++;
		}

		@Override
		public Iterator<Integer> iterator() {
			return this;
		}
		
	}
	
	
	public List<Integer> dfs() {
	    List<Integer> vertices = new ArrayList<>();
	    Stack<Integer> s = new Stack<>();
	    boolean[] visited = new boolean[V];
	    s.push(0);
	    visited[0] = true;

	    while (!s.isEmpty()) {
	        int u = s.pop();
	        vertices.add(u);

	        for (int v = 0; v < V; v++) {
	            if (!visited[v] && adjacencyMatrix[u][v]) {
	                visited[v] = true;
	                s.push(v);
	            }
	        }
	    }

	    return vertices;
	}
	
	public List<Integer> bfs() {
	    List<Integer> vertices = new ArrayList<>();
	    Queue<Integer> q = new LinkedList<>();
	    boolean[] visited = new boolean[V];
	    q.offer(0);
	    visited[0] = true;

	    while (!q.isEmpty()) {
	        int u = q.poll();
	        vertices.add(u);

	        for (int v = 0; v < V; v++) {
	            if (!visited[v] && adjacencyMatrix[u][v]) {
	                visited[v] = true;
	                q.offer(v);
	            }
	        }
	    }

	    return vertices;
	}
	
	public Queue<Integer> topologicalSort(){
		Queue<Integer> order = new LinkedList<Integer>();
		Queue<Integer> q = new LinkedList<Integer>();
		int inDegrees[] = new int[V];
		int ranks[] = new int[V];
		int rank = 0;
		
		for(int u = 0; u < V; u++) {
			inDegrees[u] = indegreeOf(u);
			if(inDegrees[u] == 0) q.offer(u);
		}
		
		while(! q.isEmpty() ) {
			int u = q.poll();
			order.offer(u);
			ranks[u] = rank++;
			
			for(int v : iterator(u)) {
				inDegrees[v]--;
				if(inDegrees[v] == 0)
					q.offer(v);
			}
			
		}
		
		// there is an directed cycle
		if(rank != V)
			return null;
			
		return order;
	}
	
	public int[][] shortestPath(int s /*source*/){
    	if(s < 0 || s >= V)
    		return null;
    	int path[] = new int[V];
    	int distance[] = new int[V];
    	Queue<Integer> q = new java.util.LinkedList<Integer>();
    	q.offer(s);
    	
    	for(int i = 0; i < V; i++) {
    		distance[i] = -1;
    	}
    	
    	path[s] = -1;
    	distance[s] = 0;
    	
    	while(! q.isEmpty()) {
    		int v = q.poll();
    		
    		for(int w : iterator(v)) {
    			if(distance[w] == -1) {
    				distance[w] = distance[v] + 1;
    				path[w] = v;
    				q.offer(w);
    			}
    		}
    	}
    	
    	return new int[][] {path, distance};
	}
	
}
