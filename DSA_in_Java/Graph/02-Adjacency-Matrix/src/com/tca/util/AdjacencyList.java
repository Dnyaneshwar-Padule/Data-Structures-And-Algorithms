package com.tca.util;

import java.util.Random;

public class AdjacencyList{

	private int V;
	private int E;
	private int edgeCount;
	private LinkedList<Integer> adjacencyList[];
	private LinkedList<Integer> inverseAdjacencyList[];
	
	@SuppressWarnings("unchecked")
	public AdjacencyList(int V) {		
		if(V < 1)
			throw new IllegalArgumentException("Too few vertices.");
		
		this.V = V;
		this.E = 0;
		this.edgeCount = 0;
		this.adjacencyList = new LinkedList[V];
		this.inverseAdjacencyList = new LinkedList[V];
		
		for(int i = 0; i < V; ++i) {
			adjacencyList[i] = new LinkedList<Integer>();
			inverseAdjacencyList[i] = new LinkedList<Integer>();
		}
	}
	
	public AdjacencyList(int V, int E) {
		this(V);
		if(E > (V * (V-1))) 
			throw new IllegalArgumentException("Too many edges");
		
		if(E < 0)
			throw new IllegalArgumentException("Too few edges");
		
		Random random = new Random();
		
		for(int i = 0; i < E; ++i) {
			int u = random.nextInt(V);
			int v = random.nextInt(V);
			addEdge(u, v);
		}		
	}
	
	public void addEdge(int u, int v) {
		if(u < 0 || u >= V || v < 0 || v >= V)
			throw new IllegalArgumentException("Invalid vertices");
		
		if(edgeCount > E)
				throw new IllegalArgumentException("Too many edges.");
		
		adjacencyList[u].add(v);
		inverseAdjacencyList[v].add(u);
		E++;
	}
	
	public int inDegree(int v) {
		if(v < 0 || v >= V)
			throw new IllegalArgumentException("Invalid vertex.");
	
		return inverseAdjacencyList[v].size();
	}

	public int outDegree(int v) {
		if(v < 0 || v >= V)
			throw new IllegalArgumentException("Invalid vertex.");
		
		return adjacencyList[v].size();
	}
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
	}
	
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("\033[92m\nAdjacency List\n\033[0m");
		
		for(int i = 0; i < V; ++i) {
			s.append( String.format("\033[95mV%d\033[0m \033[33m-->\033[0m", i));
			
			for(int vertex : adjacencyList[i]) {
				s.append( String.format("\033[36m[%2d]\033[0m \033[33m-->\033[0m", vertex) );
			}
			
			s.append("\033[31mNULL\n\033[m");
		}
		s.append("\n");
		
		s.append("\033[92mInverse Adjacency List\n\033[0m");
		
		for(int i = 0; i < V; ++i) {
			s.append( String.format("\033[95mV%d\033[0m \033[33m-->\033[0m", i));
			
			for(int vertex : inverseAdjacencyList[i]) {
				s.append( String.format("\033[36m[%2d]\033[0m \033[33m-->\033[0m", vertex) );
			}
			
			s.append("\033[31mNULL\n\033[m");
		}
		s.append("\n");
		
		return s.toString();
	}
	
}
