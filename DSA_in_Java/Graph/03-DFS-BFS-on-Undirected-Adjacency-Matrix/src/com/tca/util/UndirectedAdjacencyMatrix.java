package com.tca.util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
	
	
	public void BFS() {
		Queue<Integer> queue = new LinkedList<>();
		boolean visited[] = new boolean[V];
		queue.offer(0);
		
		while(! queue.isEmpty()) {
			int nextNode = queue.poll();
			
			if(!visited[nextNode]) {
				visited[nextNode] = true;
				System.out.println("Visited : " + nextNode);
			
				for(int i = 0; i < V; ++i)
					if(adjacencyMatrix[nextNode][i] && !visited[i])
						queue.offer(i);
			}
			else {
				System.out.println("Cycle found at : " + nextNode);
			}
		}
	
	}
	
	
	public void DFS() {
		Stack<Integer> stack = new Stack<>(); 
		boolean visited[] = new boolean[V];   // this will mark vertices as visited, if true
		stack.push(0);  // we will start from first node
		
		while( ! stack.isEmpty()) {
			int nextNode = stack.pop();
			
			// if node is not visited, visit the node and process it's adjacent nodes
			if( ! visited[nextNode]) {
				visited[nextNode] = true;  // marked the node as visited
				System.out.println("Visited : " + nextNode);
			
			
				//push adjacent unvisited nodes to the stack
				for(int i = 0; i < V; ++i) {
					
					//vertex is connected to current node(vertex) and it is not visited, then push it to the stack
					if(adjacencyMatrix[nextNode][i] && ! visited[i])
						stack.push(i);
				}
			}
			else {
				// if the next node is already visited, i.e. there is a cycle
				System.out.println("Found a cycle at " + nextNode);  
			}
		}
		
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		s.append("\033[32mUndirected Adjacency Matrix:\033[0m\033[33m\n   ");
		
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
