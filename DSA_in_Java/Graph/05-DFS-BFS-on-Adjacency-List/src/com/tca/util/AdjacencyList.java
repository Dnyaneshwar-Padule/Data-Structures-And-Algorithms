package com.tca.util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class AdjacencyList {

	private int V;
	private int E;
	private int edgeCount;
	private MyLinkedList adjacencyList[];
	
	public AdjacencyList(int V) {
		if(V < 2)
			throw new IllegalArgumentException("Too few vertices.");
		
		this.V = V;
		adjacencyList = new MyLinkedList[V];
		
		for(int i = 0; i < V; ++i) {
			adjacencyList[i] = new MyLinkedList();
		}
	}
	
	public AdjacencyList(int V, int E) {
		this(V);
		
		if(E > (V * (V-1)))
				throw new IllegalArgumentException("Too many edges."); 
		
		this.E  = E;
	
		Random random = new Random();
		for(int i = 0; i < E; i++) {
			int u = random.nextInt(V);
			int v = random.nextInt(V);
			addEdge(u,v);
		}
	}
	
	public void addEdge(int u, int v) {
		if(u < 0 || u >= V || v < 0 || v >= V)
				throw new IllegalArgumentException("Invalid vertices");
		
		if(edgeCount > E)
			throw new IllegalArgumentException("Too many edges");
		
		adjacencyList[u].add(v);
		edgeCount++;
	}
	
	public void DFS() {
		Stack<Integer> stack = new Stack<>();
		boolean visited[] = new boolean[V];

		int i = 0;
		while(i < V && adjacencyList[i].size() <= 0)
			i++;
		
		stack.push(i);
		
		while(! stack.isEmpty()) {
			int nextNode;
			nextNode = stack.pop();
		
			if(!visited[nextNode]) {
				visited[nextNode] = true;
				System.out.println("Visited : " + nextNode);
			
				for(int v : adjacencyList[nextNode]) {
					if(! visited[v])
						stack.push(v);
				}
			}
			
		
			if(stack.isEmpty() && i < V ) {
				while(i < V &&  (adjacencyList[i].size() <= 0 || visited[i]) )
					i++;
				if(i < V)stack.push(i);
			}
			
		}
	}
	
	
	public void BFS() {
		Queue<Integer> queue  = new LinkedList<>();
		boolean visited[] = new boolean[V];

		int i = 0;
		while(i < V && adjacencyList[i].size() <= 0)
			i++;
		
		queue.offer(i);
		
		while(! queue.isEmpty() ) {
			int nextNode = queue.poll();
			
			if(! visited[nextNode]) {
				visited[nextNode]  = true;
				System.out.println("Visited : " + nextNode);
			
				for(int v : adjacencyList[nextNode]) {
					if(!visited[v])
						queue.offer(v);
				}
			}
			
			if(queue.isEmpty() && i < V ) {
				while(i < V &&  (adjacencyList[i].size() <= 0 || visited[i]) )
					i++;
				if(i < V)queue.offer(i);;
			}
		}
	}
	
	public int V() {
		return V;
	}
	
	public int degree(int v) {
		if(v < 0 || v >= V)
				throw new IllegalArgumentException("Invalid vertex " + v);
		return adjacencyList[v].size();
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
		
		return s.toString();
	}
	
	
}
