package com.tca.util;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

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
	
	
	
	//Iterator
	private class ULLIterator implements ListIterator<E>{
		ListNode currentNode;
		int ptr;
		int index;
		
		ULLIterator(ListNode node) {
			this.currentNode = node;
			this.ptr = -1;
			this.index = -1;
		}
		
		@Override
		public boolean hasNext() {
			return (index < size -1);
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			ptr++;
			
			if(ptr >= currentNode.numOfElements) {
				if(currentNode.next != null) {
					currentNode = currentNode.next;
					ptr = 0;
				}
				else {
					throw new NoSuchElementException();
				}
			}
			index++;
			return (E)currentNode.elements[ptr];	
		}

		@Override
		public boolean hasPrevious() {
			return (index > 0);
		}

		@SuppressWarnings("unchecked")
		@Override
		public E previous() {
			ptr--;
			if(ptr < 0) {
				if(currentNode.previous != null) {
					currentNode = currentNode.previous;
					ptr = currentNode.numOfElements - 1;
				}
				else {
					throw new NoSuchElementException();
				}
			}
			index--;
			return (E)currentNode.elements[ptr];
		}

		@Override
		public int nextIndex() {
			return (index + 1);
		}

		@Override
		public int previousIndex() {
			return (index - 1);
		}

		@Override
		public void remove() {
			removeFromNode(currentNode, ptr);
		}

		@Override
		public void set(E e) {
			currentNode.elements[ptr] = e;
		}

		@Override
		public void add(E e) {
			insertIntoNode(currentNode, ptr + 1, e);
		}
		
	}
	
	
	
	@Override
	public ListIterator<E> iterator() {
		return new ULLIterator(firstNode);
	}
	
	
	@Override
	public int size() {
		return size; 
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) throws IndexOutOfBoundsException{
		// Check if the index is valid
		if(index < 0 || index >= size)
				throw new IndexOutOfBoundsException(index);
		
		ListNode current;
		int p;
		
		// Hee condition saangte kii
		// jar index chya pudhe, index pekshya jast elements astil tar
		// starting paasun traversal start kar
		if(size - index > index) {
			current = firstNode;
			p = 0;  // p is like, starting index of each block
				
			// (index - current.numOfElemets), mhanje index hold karnaarya block cha minimum index
			while(p <= (index - current.numOfElements)) {
				p += current.numOfElements;
				current = current.next;
			}
		}
		else {  // start traversal from last block
			current = lastNode;
			p = size;
			
			//same as above
			while( ( p-= current.numOfElements ) > index ) {
				current = current.previous;
			}
		}
		
		return (E) current.elements[index - p];
	}
	
	public boolean add(E data) {
		insertIntoNode(lastNode, lastNode.numOfElements, data);
		return true;
	}
	
	
	@Override
	public void clear() {
		ListNode current = firstNode.next;
		while(current != null) {
			ListNode next = current.next;
			current.previous = null;
			current.next = null;
			current.elements = null;
			current = next;
		}
		
		lastNode = firstNode;
		
		for(int i = 0; i < firstNode.numOfElements; ++i)
			firstNode.elements[i] = null;
		
		firstNode.numOfElements = 0;
		firstNode.next  = null;
		size = 0;
	}
	
	//Replaces an element at the specific index with specified element
	@SuppressWarnings("unchecked")
	public E set(int index, E element) throws IndexOutOfBoundsException{
		// First check, if the index is valid
		if(index < 0 || index >= size)
				throw new IndexOutOfBoundsException(index);
		
		ListNode current = null;
		E el = null;
		int pos;
		
		// start traversing from forward
		if( size - index > index ) {
			current = firstNode;
			pos = 0;
			
			// traverse till correct block
			while(pos <= (index - current.numOfElements)) {
				pos += current.numOfElements;
				current = current.next;
			}
		}
		else {  // start traversing from backward
			current = lastNode;
			pos = size;
			
			while((pos -= current.numOfElements) > index) 
				current = current.previous;	
		}
		
		el = (E)current.elements[index - pos];
		current.elements[index - pos] = element;
		return el;
	}
	
	
	// Add a new element at given position
	public void add(int index, E element) throws IndexOutOfBoundsException{
		if(index < 0 || index > size )
				throw new IndexOutOfBoundsException(index);
		
		int pos;
		ListNode current;
		
		// Traverse from first node, if the index is closer to first node
		if(size - index > index) {
			current = firstNode;
			pos = 0;   // stores starting position of every block
			
			// traverse until right block 
			while(pos <= (index - current.numOfElements)) {
				pos += current.numOfElements;
				current = current.next;
			}
		}
		else {
			current = lastNode;
			pos = size;
			
			while((pos -= current.numOfElements) > index) 
				current = current.previous;
		}
		
		insertIntoNode(current, index - pos, element);
		
	}
	
	// Removes an element from specified index
	@SuppressWarnings("unchecked")
	public E remove(int index) throws IndexOutOfBoundsException{
		// first check if index is valid
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException(index);
		
		int pos;
		E element;
		ListNode current;
		
		if(size - index > index) {
			current = firstNode;
			pos = 0;
			
			while(pos <= (index - current.numOfElements)) {
				pos += current.numOfElements;
				current = current.next;
			}
		}
		else {
			current = lastNode;
			pos = size;
			
			while((pos -= current.numOfElements) > index)
				current = current.previous;
		}
		
		element = (E)current.elements[index-pos];
		removeFromNode(current, index - pos);
		return element;
	}
	
	
	//Remove a matched element
	@Override
	public boolean remove(Object e) {
		ListNode current = firstNode;
		
		//if the element is null
		if(e == null) {
			while(current != null) {
				for(int i = 0; i < current.numOfElements; ++i) {
					if(current.elements[i] == null) {
						removeFromNode(current, i);
						return true;
					}
				}
				current = current.next;
			}
		}
		else
		{
			while(current!= null) {
				for(int i = 0; i < current.numOfElements; ++i) {
					if(e.equals(current.elements[i])) {
						removeFromNode(current, i);
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	private void insertIntoNode(ListNode node, int pos, E element) {
		
		// If all elements in the node are full, then create a new node
		// and move half elements into it
		if(node.numOfElements == nodeCapacity) {
			ListNode newNode = new ListNode();
			
			int elementsToMove = nodeCapacity / 2;
			int startingIndex = nodeCapacity - elementsToMove;
		
			// move half elements to the next node
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
		
		// shift elements
		for(int i = pos; i < node.numOfElements; ++i) {
			node.elements[i] = node.elements[i+1];
		}
		
		// as elements are shifted, last element is also shifted to second last, and last should now be null
		node.elements[node.numOfElements] = null;
		
		// check if, we can merge current node with next node or previous node
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
