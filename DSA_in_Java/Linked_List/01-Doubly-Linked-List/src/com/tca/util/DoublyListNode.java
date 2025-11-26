package com.tca.util;

public class DoublyListNode<E>{

	private DoublyListNode<E> previous;
	private E data;
	private DoublyListNode<E> next;
	
	public DoublyListNode(E data) {
		this.data = data;
		previous = null;
		next = null;
	}

	public DoublyListNode(DoublyListNode<E> previous, E data, DoublyListNode<E> next) {
		this.previous = previous;
		this.data = data;
		this.next = next;
	}

	public DoublyListNode<E> getPrevious() {
		return previous;
	}

	public void setPrevious(DoublyListNode<E> previous) {
		this.previous = previous;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public DoublyListNode<E> getNext() {
		return next;
	}

	public void setNext(DoublyListNode<E> next) {
		this.next = next;
	}

}
