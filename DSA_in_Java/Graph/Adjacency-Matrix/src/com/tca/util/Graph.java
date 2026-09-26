package com.tca.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

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
	}
	
	private void populateRandomGraph() {
		Random random = new Random();
		for(int i = 0 ; i < E ; i++) {
			int u = random.nextInt(V);
			int v = random.nextInt(V);
			add(u,v);
		}
	}
	
	/**
	 * Add an edge in undirected graph.
	 * @param u
	 * @param v
	 */
	public void add(int u, int v) {
		if(u < 0 || u >= V)
			throw new IllegalArgumentException("Invalid vertex u:" + u);
		if(V < 0 || v >= V)
			throw new IllegalArgumentException("Invalid vertex v:" + u);
		adjacencyMatrix[u][v] = true;
		adjacencyMatrix[v][u] = true;
	}
	
	/**
	 * Iterator to iterate to all adjacent vertices from u
	 * @param u
	 * @return Iterator<Boolean>
	 * @throws IllegalArgumentException
	 */
	public Iterator<Boolean> iterator(int u){
		if(u < 0 || u >= V)
				throw new IllegalArgumentException("Invalid vertex u:" + u);
		return new GIterator(u);
	}
	
	private class GIterator implements Iterator<Boolean>, Iterable<Boolean>{
		int u;
		int v;
		
		public GIterator(int u) {
			this.u = u;
		}
			
		@Override
		public Iterator<Boolean> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			for(int i = v ; i < V; i++) {
				if(adjacencyMatrix[u][v])
					continue;
				return true;
			}
			return false;
		}

		@Override
		public Boolean next() {
			if(v >= V)
				throw new NoSuchElementException();
			return adjacencyMatrix[u][v++];
		}
		
	}
	
}
