package com.tca.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;
import java.util.Stack;

public class UndirectedAdjacencyList {

	private static class LinkedList implements Iterable<Integer>{
		private static class ListNode{
			int data;
			ListNode next;
			
			ListNode(int data){
				this.data = data;
			}
			
			ListNode(int data, ListNode next){
				this.data = data;
				this.next = next;
			}
		}
		
		ListNode head;
		int n;
		
		void add(int val) {
			head = new ListNode(val, head);
			n++;
		}
		
		int size() {
			return n;
		}
		
		boolean isEmpty() {
			return head == null;
		}

		@Override
		public Iterator<Integer> iterator() {
			return new ListIterator(head);
		}
		
		private class ListIterator implements Iterator<Integer>{
			private ListNode current;
		
			
			public ListIterator(ListNode head) {
				this.current = head;
			}

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Integer next() {
				if(!hasNext())
						throw new NoSuchElementException();
				Integer data = current.data;
				current = current.next;
				return data;
			}
			
		}
	}
	
	

	
	private int V;
	private int E;
	private LinkedList adjacencyList[];
	
	public UndirectedAdjacencyList(int V) {
		if(V < 0)
			throw new IllegalArgumentException("Too few vertices, V:" + V);
		
		this.V = V;
		adjacencyList = new LinkedList[V];
		
		for(int i = 0; i < V; i++)
				adjacencyList[i] = new LinkedList();
	}
	
	public UndirectedAdjacencyList(int V, int E) {
		this(V);
		
		if(E < 0)
			throw new IllegalArgumentException("Too few edges, E:" + E);
		
		if(E > ( V * (V - 1)) / 2)
			throw new IllegalArgumentException("Too many edges, E:" + E);
		populateRandomGraph(E);
	}
	
	private void populateRandomGraph(int E) {
		Random random = new Random();
		for(int i = 0; i < E; i++) {
			int u = random.nextInt(V);
			int v = random.nextInt(V);
			add(u,v);
		}
	}
	
	
	public void add(int u, int v) {
		if(u < 0 || u >= V || v < 0 || v >= V)
			throw new IllegalArgumentException("Invalid vertices [u:" + u + ", v:" + v + "]");
		
		// undirected graph
		adjacencyList[u].add(v);
		adjacencyList[v].add(u);
		E++;
	}
	
	public boolean contains(int u, int v) {
		for(int vertex : adjacencyList[u]) {
			if(vertex == v)
				return true;
		}
		return false;
	}
	
	public int degreeOf(int v) {
		if(v < 0 || v >= V)	
			throw new IllegalArgumentException("Invalid vertex v:" + v);
		return adjacencyList[v].size();
	}
	
	public Iterable<Integer> adjacencylist(int v){
		if(v < 0 || v >= V)	
			throw new IllegalArgumentException("Invalid vertex v:" + v);
		return adjacencyList[v];
	}
	
	
	public List<Integer> dfs(){
		List<Integer> vertices = new ArrayList<Integer>();
		Stack<Integer> s = new Stack<>();
		boolean[] visited = new boolean[V];
		s.push(0);
		
		while(! s.isEmpty() ) {
			int vertex = s.pop();
			
			if(!visited[vertex]) {
				vertices.add(vertex);
				visited[vertex] = true;
			}
			
			for(Integer v : adjacencyList[vertex]) {
				if(! visited[v] )
					s.push(v);
			}
		}
		
		return vertices;
	}
	
	
	public List<Integer> bfs(){
		List<Integer> vertices = new ArrayList<Integer>();
		Queue<Integer> q = new java.util.LinkedList<>();
		boolean[] visited = new boolean[V];
		q.offer(0);
		
		while(! q.isEmpty() ) {
			int vertex = q.poll();
			
			if(!visited[vertex]) {
				vertices.add(vertex);
				visited[vertex] = true;
			}
			
			for(Integer v : adjacencyList[vertex]) {
				if(! visited[v] )
					q.offer(v);
			}
		}
		
		return vertices;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for(int u = 0; u < V; u++) {
			sb.append(u + ": ");
			for(int v : adjacencyList[u]) {
				sb.append(v + "--> ");
			}
			sb.append("null\n");
		}
		
		return sb.toString();
	}
	
}
