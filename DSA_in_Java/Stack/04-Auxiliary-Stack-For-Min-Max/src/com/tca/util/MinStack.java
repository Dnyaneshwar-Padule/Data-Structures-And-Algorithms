package com.tca.util;

public class MinStack <T extends Comparable<T>>{

	private Stack<T> elementStack;
	private Stack<T> minStack;
	
	public MinStack() {
		elementStack = new Stack<>();
		minStack = new Stack<>();
	}
	
	public void push(T data) {
		elementStack.push(data);
		try {
			if(minStack.isEmpty() || data.compareTo(minStack.peek()) < 0)
				minStack.push(data);
			else
				minStack.push(minStack.peek());
		}
		catch(Exception e) {}
	}
	
	public T pop() {
		try {
			minStack.pop();
			return elementStack.pop();
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public T getMinimum() {
		try {
			return minStack.peek();
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

	public int size() {
		return elementStack.size();
	}

	
	public boolean isEmpty() {
		return elementStack.isEmpty();
	}

	public void clear() {
		elementStack.clear();
		minStack.clear();
	}
	
}
