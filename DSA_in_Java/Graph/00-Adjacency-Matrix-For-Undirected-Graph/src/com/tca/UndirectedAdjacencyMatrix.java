package com.tca;

public class UndirectedAdjacencyMatrix {

	private boolean adjacencyMatrix[][];
	private int V;
	private int E;
	private int edgeCnt;
	
	public UndirectedAdjacencyMatrix(int V, int E) {
		if(V < 2)
			throw new IllegalArgumentException("For, V: " + V + ", too few vertices.");
		
		if(E > ( V * (V - 1) ) / 2 ) 
			throw new IllegalArgumentException("For E: " + E + ", too many edges.");
		
		this.E = E;
		this.V = V;
		edgeCnt = 0;
		adjacencyMatrix = new boolean[V][V];
	}
	
	public void addEdge(int u, int v) {
		if(u < 0 || u >= V || v < 0 || v >= V)
			throw new IllegalArgumentException("Vertices are out of array bounds.");
		
		if(v == u)
			throw new IllegalArgumentException("Self loop is not allowed in undirected graph.");
		
		if(edgeCnt == E)
			throw new IllegalArgumentException("Too many edges.");
		
		adjacencyMatrix[u][v] = true;  // u ---> v
		adjacencyMatrix[v][u] = true;  // v ---> u
		edgeCnt++;
	}
	
	public int degreeOf(int v) {
		if(v < 0 || v >= V)
			throw new IllegalArgumentException("Vertex:" +  v + " is out of array bounds.");

		int degree = 0;
		for(int i = 0; i < V; ++i )
			if(adjacencyMatrix[v][i])
				degree++;
		return degree;
	}
	
	public void printIncidentsOf(int v) {
		if(v < 0 || v >= V)
			throw new IllegalArgumentException("Vertex:" +  v + " is out of array bounds.");
		
		System.out.println("Incidents of " + v);
		for(int i = 0; i < V; ++i)
			if(adjacencyMatrix[v][i])
				System.out.println("\t{" + v + ":" + i + "}");
		
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("Undirected Adjacency Matrix:\n   ");
		
		for(int i = 0; i < V; ++i)
			s.append( String.format("%3d", i) );
		s.append("\n   ");
		
		for(int i = 0; i < V; ++i)
			s.append("---");
		s.append("\n");
		
		for(int i = 0; i < V; ++i) {
			s.append( String.format("%2d |",i) );
			for(int j = 0; j < V; ++j) 
				s.append( String.format("%3d", adjacencyMatrix[i][j] ? 1 : 0) );
			s.append("\n");
		}
		s.append("\n");	
		return s.toString();
	}
}
