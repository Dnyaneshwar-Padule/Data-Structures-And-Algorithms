package com.tca.util;

public class StaticStack {

	public static final int  CAPACITY = 50;
	private int capacity;
	private int top;
	private int stack[];
	
	public StaticStack(int capacity) {
		this.capacity = capacity;
		stack = new int[capacity];
		top = -1;
	}
	
	public StaticStack() {
		this(CAPACITY);
	}
	
	public boolean isEmpty() {
		return top < 0;
	}
	
	public boolean isFull() {
		return top == (capacity - 1);
	}
	
	public void push(int val) throws Exception {
		if( isFull() )
			throw new Exception("Stack is full.");
		stack[++top] = val;
	}
	
	public int pop() throws Exception {
		if(isEmpty())
			throw new Exception("Stack is empty."); 
		return stack[top--];
	}
	
	public int peek() throws Exception{
		if(isEmpty())
			throw new Exception("Stack is empty.");
		return stack[top];
	}
	
}
