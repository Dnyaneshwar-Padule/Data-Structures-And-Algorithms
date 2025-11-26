package com.tca.util;

public class MyDoublyLinkedList <E extends Comparable<E>>{
	
	private int length;
	private DoublyListNode<E> head;
	private DoublyListNode<E> tail;
	
	public MyDoublyLinkedList() {
		head = null;
		tail = null;
		length = 0;
	}
	
	public synchronized int size() {
		return length;
	}
	
	public synchronized void addFirst(E data) {
		DoublyListNode<E> newNode = new DoublyListNode<>(data);
		if(head == null) {
			tail = newNode;
			head = newNode;
		}
		else {
			head.setPrevious(newNode);
			head = newNode;
		}
		length++;
	}
	
	public synchronized void addLast(E data) {
		DoublyListNode<E> newNode = new DoublyListNode<>(data);
		if(head == null) {
			head = newNode;
			tail = newNode;
		}
		else {
			newNode.setPrevious(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		length++;
	}
	
	public synchronized void insert(E data, int index) {
		if(index < 0) index = 0;
		else if(index > length) index = length;
		
		DoublyListNode<E> newNode = new DoublyListNode<>(data);
		
		if(head == null) {
			head = newNode;
			tail = newNode;
		}
		else if(index == 0) {
			newNode.setNext(head);
			head.setPrevious(newNode);
			head = newNode;
		}
		else if(index == length) {
			newNode.setPrevious(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		else {
			DoublyListNode<E> current = null;
			for(int i = 1; i < index; ++i)
				current = current.getNext();
			newNode.setPrevious(current);
			newNode.setNext(current.getNext());
			if(current.getNext() != null) current.getNext().setPrevious(newNode);
			current.setNext(newNode);
		}
		length++;
	}

	public synchronized E getHead() {
		if(head == null)
			return null;
		return head.getData();
	}
	
	public synchronized E getTail() {
		if(tail == null)
			return null;
		return tail.getData();
	}
	
	public synchronized void removeHead() {
		if(head == null)	
			return;
		else if(head == tail)
				head = tail = null;
		else {
			head.getNext().setPrevious(null);
			head = head.getNext();
		}
		length--;
	}
	
	public synchronized void removeTail() {
		if(tail == null)
			return;
		else if(tail == head)
			tail = head = null;
		else {
			tail.getPrevious().setNext(null);
			tail = tail.getPrevious();
		}
		length--;
	}
	
	public synchronized void remove(int pos) {
		if(pos < 0) pos = 0;
		else if(pos >= length) pos = length - 1;
		
		if(head == null)
			return;
		
		if(pos == 0)
			removeHead();
		else if(pos == length-1)
				removeTail();
		else {
			DoublyListNode<E> current = head, next = null;
			for(int i = 1; i < pos; ++i)
				current = current.getNext();
		
			next = current.getNext();
			current.setNext(next.getNext());
			if(next.getNext() != null) next.getNext().setPrevious(current); 
			length--;
		}
	}
	
	public synchronized void removeMatched(E data) {
		if(head == null)
			return;
		
		if(head.getData().compareTo(data) == 0)
			removeHead();
		else if(tail.getData().compareTo(data) == 0)
			removeTail();
		else {
			DoublyListNode<E> current = head.getNext();
			while(current.getNext() != null) {
				if(current.getData().compareTo(data) == 0) {
					current.getNext().setPrevious(current.getPrevious());
					current.getPrevious().setNext(current.getNext());
					length--;
					break;
				}
				current = current.getNext();
			}
		}	
	}
	
	
	public synchronized E get(int index) {
		if(head == null)
			return null;
		
		if(index < 0) index = 0;
		else if(index > length) index = length;
		
		DoublyListNode<E> current = head;
		for(int i = 0; i < index; ++i) {
			current = current.getNext();
		}
		
		return current.getData();
	}
}
