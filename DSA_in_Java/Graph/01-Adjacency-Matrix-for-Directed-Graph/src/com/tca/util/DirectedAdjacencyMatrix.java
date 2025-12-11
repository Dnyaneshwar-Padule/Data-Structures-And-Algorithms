package com.tca.util;

public class DirectedAdjacencyMatrix {

	private boolean adjacencyMatrix[][];
	private int V;
	private int E;
	private int edgeCount;
	
	public DirectedAdjacencyMatrix(int V, int E) {	
		if(V < 2)
			throw new IllegalArgumentException("Too few vertices.");
	
		if(E > (V * (V - 1)))
			throw new IllegalArgumentException("Too many edges.");
			
		this.E = E;
		this.V = V;
		edgeCount = 0;
		adjacencyMatrix  = new boolean[V][V];
	}	
	
	// from u to v (u ---> v)
	public void addEdge(int u, int v) {
		if(u < 0 || u >= V || v < 0 || v >= V)
			throw new IllegalArgumentException("Vertex is out of array bounds.");
		
		if(edgeCount == E)
				throw new IllegalArgumentException("Too many edges.");
		
		adjacencyMatrix[u][v] = true;
		edgeCount++;
	}
	
	public int inDegreeOf(int v) {
		if(v < 0 || v >= V)
			throw new IllegalArgumentException("Vertex:" + v + " is out of array bounds." );
	
		int cnt = 0;
		for(int i = 0; i < V; ++i)
			if(adjacencyMatrix[i][v])
				cnt++;
		return cnt;
	}

	public int outDegreeOf(int v) {
		if(v < 0 || v >= V)
			throw new IllegalArgumentException("Vertex:" + v + " is out of array bounds." );
		
		int cnt = 0;
		for(int i = 0; i < V; ++i)
			if(adjacencyMatrix[v][i])
				cnt++;
		return cnt;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("\033[32m\nDirected Adjacency Matrix:\033[0m\033[33m\n    ");
		
		for(int i = 0; i < V; ++i)
			s.append( String.format("%3d", i) );
		s.append("\n    ");
		
		for(int i = 0; i < V; ++i)
			s.append("---");
		s.append("\n\033[0m");
		
		for(int i = 0; i < V; ++i) {
			s.append( "\033[33m" + String.format("%2d |",i) + "\033[0m" );
			for(int j = 0; j < V; ++j) {
				if(adjacencyMatrix[i][j]) 
					s.append("\033[36m" +  String.format("%3d", 1)  + "\033[0m");
				else
					s.append("\033[35m" +  String.format("%3d", 0)  + "\033[0m");
			}
			s.append("\n");
		}
		s.append("\n");	
		return s.toString();
	}
}
