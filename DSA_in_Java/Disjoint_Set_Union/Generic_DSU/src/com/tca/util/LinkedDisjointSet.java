package com.tca.util;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class LinkedDisjointSet <T> implements DisjointSet<T> {

	private static class Node<E>{
		E data;
		Node<E> parent;
		int rank;
		
		Node(E data){
			this.data = data;
			parent = this;
			rank = 0;
		}
	}
	
	private Map<T, Node<T>> registry;
	private int totalSets;
	
	public LinkedDisjointSet() {
		registry = new HashMap<>();
		totalSets = 0;
	}
	

	@Override
	public boolean makeSet(T element) {
		if(element == null)
			throw new IllegalArgumentException("Element can not be null.");
		
		if(registry.containsKey(element))
			return false;
		
		registry.put(element, new Node<>(element));
		totalSets++;
		return true;
	}

	@Override
	public T find(T element) {
		Node<T> node = getNodeOrThrow(element);
		return find(node).data;
	}
	
	private Node<T> find(Node<T> node) {
		if(node.parent == node)
			return node;
		node.parent = find(node.parent);
		return node.parent;
	}

	private Node<T> improvedFind(Node<T> node){
		Node<T> root = node;
		
		// traverse to the absolute root
		while(root.parent != root) {
			root = root.parent;
		}
		
		// compress path
		Node<T> cur = node;
		while(cur != root) {
			Node<T> next = cur.parent;
			cur.parent = root;
			cur = next;
		}
		
		return root;
	}
	
	@Override
	public boolean union(T element1, T element2) {
		Node<T> element1Representator = find(getNodeOrThrow(element1));
		Node<T> element2Representator = find(getNodeOrThrow(element2));
		
		if(element1Representator == element2Representator)
			return false;
		
		if(element1Representator.rank < element2Representator.rank)
			element1Representator.parent = element2Representator;
		else if(element1Representator.rank > element2Representator.rank)
			element2Representator.parent = element1Representator;
		else {
			element2Representator.parent = element1Representator;
			element1Representator.rank++;
		}
		
		totalSets--;
		return true;
	}

	@Override
	public boolean isConnected(T element1, T element2) {
		if(! registry.containsKey(element1) || ! registry.containsKey(element2))
			return false;
		return find(getNodeOrThrow(element1)) == find(getNodeOrThrow(element2));
	}

	@Override
	public int size() {
		return registry.size();
	}

	private Node<T> getNodeOrThrow(T element){
		Node<T> node = registry.get(element);
		if(node == null)
			throw new NoSuchElementException("Element '" + element + "' is not registered in this DisjointSet. Call makeSet() first.");
		return node;
	}


	@Override
	public int totalSets() {
		return totalSets;
	}


	@Override
	public boolean elementExists(T element) {
		return registry.containsKey(element);
	}
	
}
