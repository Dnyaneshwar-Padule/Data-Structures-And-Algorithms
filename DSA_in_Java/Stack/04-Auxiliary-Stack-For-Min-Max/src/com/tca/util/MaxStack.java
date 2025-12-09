package com.tca.util;

public class MaxStack<T extends Comparable<T>> {

	private Stack<T> elementStack;
	private Stack<T> maxStack;
	
	public MaxStack() {
		elementStack = new Stack<T>();
		maxStack = new Stack<T>();
	}
	
	public void push(T data) {
		elementStack.push(data);
		
		try {
			if(maxStack.isEmpty() || data.compareTo(maxStack.peek()) >= 0) {
				maxStack.push(data);
			}
			else {
				maxStack.push( maxStack.peek() );
			}
		}
		catch(Exception e) {}
	}

	public T pop() {
		try {
			maxStack.pop();
			return elementStack.pop();
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public T peek() {
		try {
			return elementStack.peek();
		}
		catch(Exception e) {
			return null;
		}
	}

	
	public T getMaximum() {
		try {
			return maxStack.peek();
		}catch(Exception e) {
			return null;
		}
	}
	
	public boolean isEmpty() {
		return elementStack.isEmpty();
	}
	
	public int size() {
		return elementStack.size();
	}

	public void clear() {
		elementStack.clear();
		maxStack.clear();
	}
}
