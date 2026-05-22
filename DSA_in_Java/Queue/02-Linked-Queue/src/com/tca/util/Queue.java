package com.tca.util;

public class Queue <T> {
	
	// list node
	private class Node<T>{
		private T data;
		private Node<T> next;
	
		Node(T data){
			this.data = data;
		}
		
		Node(Node<T> next){
			this.next = next;
		}
		
		Node(T data, Node<T> next){
			this.data = data;
			this.next = next;
		}
	}
	
	
	private Node<T> head;  // front
	private Node<T> tail; // rear
	private Integer size;
	
	public Queue() {
		head = null;
		tail = null;
		size = 0;
	}
	
	public int size() {
		return size;	
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public void enQueue(T data) {
		Node<T> newNode = new Node<>(data);
	
		if(tail == null) {
			head = tail = newNode;
		}
		else {
			tail.next = newNode;
			tail = newNode;
		}
		
		size++;
	}
	
	
	public T deQueue() {
		if(isEmpty())
			throw new IllegalStateException("Queue is empty: Underflow");

		T data = head.data;
		
		if(head == tail)
			head = tail = null;
		else
			head = head.next;
		
		size--;
		return data;
	}
}
