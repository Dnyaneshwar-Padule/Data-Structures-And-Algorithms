package com.tca.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class WeightedDirectedAdjacencyList {

	private static class Pair{
		int vertex;
		int weight;
		
		Pair(int vertex, int weight){
			this.vertex = vertex;
			this.weight = weight;
		}
	}
	
	private static class LinkedList implements Iterable<Pair>{
		
		private static class ListNode{
			int data;
			int weight;
			ListNode next;
			
			ListNode(int data){
				this.data = data;
			}
			
			ListNode(int data, int weight){
				this.data = data;
				this.weight = weight;
			}
			
			ListNode(int data, int weight, ListNode next){
				this.data = data;
				this.weight = weight;
				this.next = next;
			}
			
		}
		
		ListNode head;
		int size;
		
		boolean isEmpty() {
			return head == null;
		}
		
		int size() {
			return size;
		}
		
		void add(int data, int weight) {
			head = new ListNode(data, weight, head);
			size++;
		}
		
		@Override
		public Iterator<Pair> iterator() {
			return new ListIterator(head);
		}
		
		boolean contains(int w) {
			for(Pair v : this ) {
				if(v.vertex == w)
					return true;
			}
			return false;
		}
		
		class ListIterator implements Iterator<Pair>{
			ListNode current;
			
			ListIterator(ListNode current){
				this.current = current;
			}
			
			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Pair next() {
				if(!hasNext()) throw new NoSuchElementException();
				int data = current.data;
				int weight = current.weight;
				current = current.next;
				return new Pair(data, weight);
			}
		}
	}
	
	
	private final int V;
	private int E;
	private LinkedList[] adjacencyList;
    private int[] inDegree;
	
	
	public WeightedDirectedAdjacencyList(int V) {
		if(V < 1) throw new IllegalArgumentException("Too few vertices.");
		this.V = V;
		adjacencyList = new LinkedList[V];
		inDegree = new int[V];
		for(int i = 0; i < V; i++) {
			adjacencyList[i] = new LinkedList();
		}
	}
	
	public WeightedDirectedAdjacencyList(int V, int E) {
        this(V);
        if (E < 0) 
        	throw new IllegalArgumentException("Too few edges, E:" + E);
        if (E > V * (V - 1))
            throw new IllegalArgumentException("Too many edges, E:" + E);
        populateRandomGraph(E);
		
	}
	
    private void populateRandomGraph(int E) {
        Random random = new Random();

        while (E != this.E) {
            int u = random.nextInt(V);
            int v = random.nextInt(V);
            int weight = random.nextInt(100);
            add(u, v, weight);
        }
    }

    public void add(int u, int v, int weight) {
        if (u < 0 || u >= V || v < 0 || v >= V)
            throw new IllegalArgumentException("Invalid vertices [u:" + u + ", v:" + v + "]");

        if (u == v || contains(u, v))
            return;

        adjacencyList[u].add(v, weight);

        // Edge u -> v
        // increases the in-degree of v
        inDegree[v]++;
        E++;
    }

    public boolean contains(int u, int v) {
        if (u < 0 || u >= V || v < 0 || v >= V)
            throw new IllegalArgumentException("Invalid vertices [u:" + u + ", v:" + v + "]");

        for (Pair w : adjacencyList[u]) {
            if (w.vertex == v)
                return true;
        }

        return false;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    // Out-degree
    public int outdegreeOf(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Invalid vertex v:" + v);
        return adjacencyList[v].size();
    }

    // In-degree
    public int indegreeOf(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Invalid vertex v:" + v);
        return inDegree[v];
    }

    public Iterable<Pair> adjacencyList(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("Invalid vertex v:" + v);
        return adjacencyList[v];
    }

    public List<Integer> dfs() {
        List<Integer> vertices = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        boolean[] visited = new boolean[V];
        s.push(0);
        visited[0] = true;

        while (!s.isEmpty()) {
            int vertex = s.pop();
            vertices.add(vertex);
            
            for (Pair v : adjacencyList[vertex]) {
                if (!visited[v.vertex]) {
                    visited[v.vertex] = true;
                    s.push(v.vertex);
                }
            }
        }

        return vertices;
    }

    public List<Integer> bfs() {
        List<Integer> vertices = new ArrayList<>();
        Queue<Integer> q = new java.util.LinkedList<>();
        boolean[] visited = new boolean[V];
        q.offer(0);
        visited[0] = true;

        while (!q.isEmpty()) {
            int vertex = q.poll();
            vertices.add(vertex);

            for (Pair v : adjacencyList[vertex]) {
                if (!visited[v.vertex]) {
                    visited[v.vertex] = true;
                    q.offer(v.vertex);
                }
            }
        }

        return vertices;
    }
	
    
    /**
     * 
     * @param s
     * @return int[][], where int[0] is path[V] and int[1] is distance[V] 
     */
    public int[][] dijkstra(int s /* source */) {
    	final long infinity = Integer.MAX_VALUE;
    	boolean[] reached = new boolean[V];
    	int[] distance = new int[V];
    	int[] path = new int[V];
   
    	/* distance to other vertices is unknown */
    	for(int i = 0; i < V; i++) {
    		distance[i] = (int)infinity;
    		path[i] = -1;
    	}
    	
    	distance[s] = 0;  /* source to source distance is 0 */
    	
    	/* check adjacent vertices to source
    	for(Pair v : adjacencyList[s]) {
    		distance[v.vertex] = v.weight;
    		path[v.vertex] = s;
    	}
    	 * */
    	
    	int n,m;
    	for(int k = 0; k < V-1; k++) {
    		
    		m = -1;
    		
    		for(int v = 0; v < V; v++) {
    			if(! reached[v] && 
    				(m == -1 || distance[m] > distance[v] )	)
    				m = v;
    		}
    		
    		// no more reachable nodes
    		if(m == -1 || distance[m] == infinity)
    			break;
    		
    		// add to reached
    		reached[m] = true;
    		
    		/* find possible shortcuts, from m */
    		for(Pair v : adjacencyList[m]) {
    			if( ! reached[v.vertex] ) {
    				if((long)distance[m] + v.weight < distance[v.vertex] ) {
    					distance[v.vertex] = distance[m]  +v.weight;
    					path[v.vertex] = m;
    				}
    			}
    		}
    	}
    	
    	return new int[][] {path, distance};
    	
    }
    
}
