package com.tca.util;

public class Stack <T>{

	private ListNode top;
	private int size;
	
	
	public Stack() {
		top = null;
		size = 0;
	}
	
	private class ListNode{
		T data;
		ListNode next;
		
		ListNode(T data, ListNode next){
			this.data = data;
			this.next = next;
		}	
	}
	
	
	public int size() {
		return size;
	}
	
	public void push(T data) {
		top = new ListNode(data, top);
		size++;
	}
	
	public boolean isEmpty() {
		return top == null;
	}
	
	public T pop() throws Exception{
		if(isEmpty())
			throw new Exception("Stack is empty.");
		T data = top.data;
		top = top.next;
		size--;
		return data;
	}
	
	
	public T peek() throws Exception{
		if(isEmpty())
			throw new Exception("Stack is empty.");
		return top.data;
	}
	
}
