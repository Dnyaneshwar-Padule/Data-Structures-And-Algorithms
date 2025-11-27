package com.tca.util;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.List;

public class MyUnrolledLinkedList<E> extends AbstractList<E> implements Serializable, List<E> {

	private static final long serialVersionUID = -8535079294142920929L;

	// maximum elements that can be held by a block
	private int nodeCapacity;
	
	//size of list
	private int size;
	
	//The first node of this list
	private ListNode firstNode;
	
	//The last node of the list
	private ListNode lastNode;
	
	
	public MyUnrolledLinkedList(int nodeCapacity) throws IllegalArgumentException{
		if(nodeCapacity < 8) 
			throw new IllegalArgumentException("Node capacity should be > 8");
	
		this.nodeCapacity = nodeCapacity;
		size = 0;
		firstNode = new ListNode(); // initially there will be a empty block
		lastNode = firstNode;
	}
	
	public MyUnrolledLinkedList() {
		this(16);
	}
	
	
	// It is like a block
	// next will contain reference of next block
	//previous will contain reference of previous block
	// elements (array) will contain elements
	// numOfElements will contain, no of elements in in that block
	private class ListNode{
		ListNode next;
		ListNode previous;
		int numOfElements;
		Object[] elements;
		ListNode(){
			elements = new Object[nodeCapacity];
			next = null;
			previous = null;
			numOfElements = 0;
		}
	}
	
	@Override
	public int size() {
		return size; 
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	
	
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void insertIntoNode(ListNode node, int pos, E element) {
		
		// If all elements in the node are full, then create a new node
		// and move half elements into it
		if(node.numOfElements == nodeCapacity) {
			ListNode newNode = new ListNode();
			
			int elementsToMove = nodeCapacity / 2;
			int startingIndex = nodeCapacity - elementsToMove;
		
			for(int i = 0; i < elementsToMove; ++i) {
				newNode.elements[i] = node.elements[startingIndex + i];
				node.elements[startingIndex + i] = null;
			}
			
			// Update element count
			node.numOfElements -= elementsToMove;
			newNode.numOfElements = elementsToMove;
			
			//update pointers, as the newNode will be attached next to node
			newNode.next = node.next;
			newNode.previous = node;
			if(node.next != null) node.next.previous = newNode;
			node.next = newNode;
			if(node == lastNode) lastNode = newNode;
			
			// For correct element placement
			// check if the element should be inserted into the node or newNode
			if(pos > node.numOfElements) {
				node = newNode;
				pos -= node.numOfElements;
			}
		}
		
		// Shift elements, to add new element
		for(int i = node.numOfElements; i > pos; ++i)
			node.elements[i] = node.elements[i-1]; 
	
		node.elements[pos] = element;
		node.numOfElements++;
		size++;
	}
	
	private void removeFromNode(ListNode node, int pos) {
		node.numOfElements--;
		for(int i = pos; i < node.numOfElements; ++i) {
			node.elements[i] = node.elements[i+1];
		}
		node.elements[node.numOfElements] = null;
		if(node.next != null && node.numOfElements + node.next.numOfElements <= nodeCapacity) {
			mergeWithNextNode(node);
		}
		else if(node.previous != null && node.numOfElements + node.previous.numOfElements <= nodeCapacity) {
			mergeWithNextNode(node.previous);
		}
	}
	
	
	//This function merges a node with next node
	// All elements are stored in current node
	// next node is freed
	private void mergeWithNextNode(ListNode node) {
		ListNode next = node.next;
		
		//Insert all elements from next node into current node (at end)
		for(int i = 0; i < next.numOfElements; i++) {
			node.elements[node.numOfElements + i] = next.elements[i];
			next.elements[i] = null;
		}

		// as elements are moved in the current node, adjust nodeCount in the current node
		node.numOfElements += next.numOfElements;
		
		// As next node will be freed, update pointers
		if(next.next != null)
			next.next.previous = node;
		
		node.next = next.next;
		
		if(next == lastNode)
			lastNode = node;
	}

}
