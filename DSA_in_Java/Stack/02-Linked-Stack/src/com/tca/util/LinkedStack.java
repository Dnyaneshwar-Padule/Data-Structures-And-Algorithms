package com.tca.util;

public class LinkedStack<T> {

	private int size;
	private ListNode top;
	
	private class ListNode{
		T data;
		ListNode next;
		
		ListNode(T data){
			this.data = data;
		}
		
		ListNode(T data, ListNode next){
			this.data = data;
			this.next = next;
		}
	}
	
	public LinkedStack() {
		size = 0;
		top = null;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public int size() {
		return size;
	}
	
	public void push(T data) {
		ListNode newNode = new ListNode(data, top);
		top = newNode;
		size++;
	}
	
	public T pop()throws Exception {
		if(isEmpty())
			throw new Exception("Stack is empty.");
		T data = top.data;
		top = top.next;
		size--;
		return data;
	}
	
	public T top() throws Exception{
		if(isEmpty())
			throw new Exception("Stack is empty.");
		return top.data;
	}
	
}
