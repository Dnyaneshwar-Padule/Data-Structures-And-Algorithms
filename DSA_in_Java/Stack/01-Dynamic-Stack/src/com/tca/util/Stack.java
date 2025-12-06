package com.tca.util;

import java.util.function.DoubleBinaryOperator;

public class Stack {

	private int top;
	private int capacity;
	public static final int MINCAPACITY = 1<<15;
	private int[] stack;
	
	public Stack(int capacity) {
		if(capacity < 1)
			throw new IllegalArgumentException("Capacity should be greater than 0.");
		this.capacity = capacity;
		stack = new int[capacity];
		top = -1;
	}

	//default capacity
	public Stack() {
		this(16);
	}
	
	public boolean isEmpty() {
		return top < 0;
	}
	
	private void expand() {
		int length = size();
		int[] newStack = new int[length * 2];
		System.arraycopy(stack, 0, newStack, 0, length);
		stack = newStack;
		this.capacity = this.capacity << 1;
	}
	
	public int size() {
		return top+1;
	}
	
	public void push(int val) {
		if(size() == capacity)
			expand();
		stack[++top] = val;
	}
	
	private void shrink() {
		int length = top + 1;
		//                          top * 2 >= length, false when top = 0 
		if(length <= MINCAPACITY && top<<2 >= length )
			return;
		
		length = length + (top << 1);
		if(top < MINCAPACITY)
			length = MINCAPACITY;
		
		int[] newStack = new int[length];
		System.arraycopy(stack,  0, newStack, 0, top + 1);
		stack = newStack;
		capacity = length;
	}
	
	public int pop() throws Exception{	
		if(isEmpty())
			throw new Exception("Stack is empty.");
		int data =  stack[top--];
		shrink();
		return data;
	}
	
	public int top() throws Exception{
		if(isEmpty())
			throw new Exception("Stack is empty.");
		return stack[top];
	}
	
}
