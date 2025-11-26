package com.tca.util;

public class MyLinkedList<E extends Comparable<E>> {

	private ListNode<E> head;
	int length;

	public MyLinkedList() {	
		head = null;
		length = 0;
	}
	
	/*
	public MyLinkedList(E... values) {
		
	}
	*/
	public synchronized int size() {
		return length;
	}
	
	public synchronized void addFirst(E data) {
		ListNode<E> node = new ListNode<>(data);
		node.setNext(head);
		head = node;
		length++;
	}
	
	public synchronized void addLast(E data) {
		ListNode<E> node = new ListNode<>(data);
		ListNode<E> current, previous;
		if(head == null) {
			head = node;
		}
		else {
			for(previous = head; (current = previous.getNext()) != null; previous = current);			
			previous.setNext(node);
		}
		length++;
	}
	
	public synchronized void insert(E data, int index) {
		if( index < 0) index = 0;
		else if(index > length) index = length;
		
		ListNode<E> node = new ListNode<>(data);
		
		if(head == null) {
			head = node;
		}
		else if (index == 0) {
			node.setNext(head);
			head = node;
		}
		else {
			ListNode<E> current = head;
			for(int i = 1 ; i < index ; ++i) 
				current = current.getNext();
			node.setNext(current.getNext());
			current.setNext(node);
		}
		length++;
	}
	
	public synchronized E removeFromBegin() {
		if(head == null)
			return null;
		ListNode<E> temp = head;
		head.setNext(head.getNext());
		temp.setNext(null);
		length--;
		return temp.getData();
	}
	
	public synchronized E removeFromEnd() {
		if(head == null)
				return null;
		ListNode<E> current = head, previous = null, next = head.getNext();
		
		if(next == null) {
			head = null;
		}
		else {
			while( (next = current.getNext()) != null ) {
				previous = current;
				current = next;
			}
			previous.setNext(null);
		}

		length--;
		return current.getData();
	}
	
	public synchronized void remove(int index) {
		if (index < 0) index = 0;
		if(index >= length) index = length - 1;
		
		ListNode<E> current = head;
		
		if(head == null) {
			return;
		}
		
		if(index == 0) {
			head = head.getNext();
		}
		else {
			for(int i = 1; i < index; ++i)
				current = current.getNext();
			current.setNext(current.getNext().getNext());
		}
		length--;
	}
	
	public synchronized E get(int index) {
		if(head == null) return null;
		else if(index < 0) index = 0;
		else if (index >= length) index = length-1;
		
		ListNode<E> current = head;		
		
		for(int i = 0; i < index; ++i)
			current = current.getNext();

		return current.getData();
	}
	
	public synchronized int getPosition(E data) {
		ListNode<E> traverser = head;
		int pos = 0;
		while(traverser != null) {
			if(traverser.getData().compareTo(data) == 0)
				return pos;
			++pos;
			traverser = traverser.getNext();
		}
		return -1;
	}
	
	
	public synchronized void removeMatched(E data) {
		if(head == null)
			return;
		
		if(data.compareTo(head.getData()) == 0) {
			head = head.getNext();
			return;
		}
		
		ListNode<E> current = head, next = null;
		
		while( (next = current.getNext()) != null ) {
			if(next.getData().compareTo(data) == 0) {
				current.setNext(next.getNext());
				length--;
				return;
			}
			current = next;
		}
		
	}
	
	public synchronized void clearList() {
		head = null;
		length = 0;
	}

	
}
