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
	
		/*
		Random random = new Random();
		for(int i = 0; i < E; i++) {
			int u = random.nextInt(V);
			int v = random.nextInt(V);
			addEdge(u,v);
		}
		*/
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
	
	
	public void shortestPath(int src) {
		if(src < 0 || src >= V)
				throw new IllegalArgumentException("Invalid source Index :" + src);;
		
		Queue<Integer> queue = new LinkedList<>();
		int distance[] = new int[V];
		int path[] = new int[V];      // will store the previous, vertex (or the vertex who discovered the current vertex)
		queue.offer(src);  // start from source
		
		// Initial distance from source to remaining vertices is -1 (which is not measured)
		for(int i = 0; i < V; ++i)
			distance[i] = -1;   
		
		distance[src] = 0; // the distance from source to source is 0
		path[src] = src;  // there is no previous path form source, so it's path is set to itself
		
		while( ! queue.isEmpty()) {
			int v = queue.poll();  // the current vertex
			
			// go through all connected vertices of current vertex
			for(int w : adjacencyList[v]) {
				// if the distance of connected vertex is -1, i.e. that vertex's distance is not measured, so measure it
				if(distance[w] == -1) {
					distance[w] = distance[v] + 1;  // the distance of connected vertex is one more step from current vertex, i.e v
					path[w] = v;  // path is saving the previous connected vertex (or the vertex, who discovered it, it means w)
					queue.offer(w);  // add the connected vertex to the queue, so that we can further discover new vertices (which are connected to w)
				
					// We have discovered this vertex (w), so print it's distance and also the path
					System.out.print("Shortest distance from " + src + " to " + w  + " is " + distance[w] + " with path : ");				
				
					// The following loop prints path (in reverse)
					
					int previousPath = w;  // so we are starting from the newly discovered node, that is w
					while(previousPath != path[previousPath]) {   // if the previous vertex of this vertex is same as him, i.e this is the source, so break the loop here
						System.out.print(previousPath + " <-- ");  // print the vertex in the path
						previousPath = path[previousPath];        // and, go to the previous vertex, and do same for that vertex
					}
					System.out.println(src); // loop breaks, before printing the source, so, print source after the loop
				}
			}
		}
		
	}

	
	
	
	public int V() {
		return V;
	}
	
	public int E() {
		return E;
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
