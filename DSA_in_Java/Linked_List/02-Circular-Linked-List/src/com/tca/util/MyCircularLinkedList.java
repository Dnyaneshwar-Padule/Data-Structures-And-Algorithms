package com.tca.util;

public class MyCircularLinkedList<E extends Comparable<E>> {

	private ListNode<E> tail;
	private int length;
	
	public int size() {
		return length;
	}
	
	public void add(E data) {
		addToHead(data);
	}
	
	public void addToHead(E data) {
		ListNode<E> newNode = new ListNode<>(data);
		if(tail == null) {
			tail = newNode;
		}
		else {
				newNode.setNext( tail.getNext() );
				tail.setNext(newNode);
		}
		length++;
	}
	
	public void addTail(E data) {
		addToHead(data);
		tail.setNext(tail.getNext());
	}
	
	public E peek() {
		if(tail == null)
			return null;
		return tail.getNext().getData();
	}
	
	public E tailPeek() {
		if(tail == null)
			return null;
		return tail.getData();
	}
	
	public E removeFromTail() {
		if(tail == null)
			return null;
		
		ListNode<E> tailPrev = tail.getNext(), temp = tail;
		while(tailPrev.getNext() != tail)
			tailPrev = tailPrev.getNext();
		
		if(tailPrev == tail)
			tail = null;
		else {
			tailPrev.setNext(tail.getNext());
			tail = tailPrev;
		}
		length--;
		return temp.getData();
	}
	
	public E removeFromHead() {
		if(tail == null)
			return null;
		
		ListNode<E> temp = tail.getNext();
		if(tail == tail.getNext()) {
			tail = null;
		}
		else {
			tail.setNext(temp.getNext());
			temp.setNext(null);
		}
		length--;
		return temp.getData();
	}
	
	public boolean contains(E data) {
		if(tail == null)
			return false;
	
		ListNode<E> current = tail.getNext();
		while( current != tail && ( current.getData().compareTo(data) ) != 0 )
			current = current.getNext();
		return current.getData().compareTo(data) == 0;
	}
	
	public void removeHead() {
		if(tail == null)
			return;
		ListNode<E> head = tail.getNext();
		
		if(head == tail) {
			tail = null;
			head.setNext(null);
		}
		else {
			tail.setNext(head.getNext());
			head.setNext(null);
		}
		length--;
	}
	
	public void removeTail() {
		if(tail == null)
			return;
		
		ListNode<E> current = tail.getNext(), temp = tail;	
		while(current.getNext() != tail)
			current = current.getNext();
		
		if(current == tail) {
			tail = null;
			temp.setNext(null);
		}
		else {
			current.setNext(tail.getNext());
			tail = current;
			temp.setNext(null);
		}
		length--;
	}
	
	public void remove(E data) {
		if(tail == null)
			return;
		
		ListNode<E> current = tail.getNext(), prev = tail;
		while(current != tail && (current.getData().compareTo(data)) != 0  ) {
			prev = current;
			current = current.getNext();
		}
		
		if(current.getData().compareTo(data) == 0) {
			if(tail == tail.getNext() ) {
				tail = null;
			}
			else {
				prev.setNext(current.getNext());
				if(current == tail)
					tail = prev;
			}
			current.setNext(null);
			length--;
		}	
	}
	
	public void clear() {
		tail = null;
		length = 0;
	}
	
	@Override
	public String toString() {
		if (tail == null)
			return "[]";
		
		StringBuilder result = new StringBuilder("[");
		result.append( tail.getData().toString() );
		ListNode<E> current = tail.getNext();
		while(current != tail) {	
			result.append( ", " + current.getData().toString() );
			current = current.getNext();
		}
		
		return result.append("]").toString();
	}
}
