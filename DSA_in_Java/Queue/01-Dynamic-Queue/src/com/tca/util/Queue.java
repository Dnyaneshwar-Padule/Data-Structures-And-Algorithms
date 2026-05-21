package com.tca.util;

public class Queue {

	private int[] queue;
	private int front;
	private int rear;
	private int size;
	private int capacity;
	private static final int DEFAULT_CAPACITY = 16;
	
	public Queue() {
		front = 0;
		rear = 0;
		size = 0;
		capacity = DEFAULT_CAPACITY;
		queue = new int[capacity];
	}
	
	public Queue(int capacity) {
		if(capacity < 1)
			throw new IllegalArgumentException("Invalid capacity : " + capacity);
	
		front = 0;
		rear = 0;
		size = 0;
		this.capacity = capacity;
		queue = new int[capacity];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
		// return front == rear;
	}
	
	public void enQueue(int val) {
		if(size == capacity)
			expand();
		
		queue[rear] = val;
		rear = (rear + 1) % capacity;
		size++;
	}
	
	public int deQueue() {
		if(isEmpty())
			throw new IllegalStateException("Queue is empty: Underflow");
	
		int val = queue[front];
		front = (front + 1) % capacity;
		size--;
		shrink();
		return val;
	}
	
	private void expand() {
		int length = size();
		int newQueue[] = new int[length << 1];
		
		int i,j;
		for(i = front, j = 0; i != rear; i = (i + 1) % capacity) {
			newQueue[j] = queue[i];
			j++;
		}
		newQueue[j] = queue[i];
		
		capacity = length << 1;
		front = 0;
		rear = length;
		queue = newQueue;
	}
	
	private void shrink() {
		int length = size();
		
		if(length << 2 >= capacity)
			return;
	
		length = capacity >> 1;
	
		
		int[] newQueue = new int[length]; 
		
		int i,j;
		for(i = front, j = 0; i != rear ; i = (i + 1) % capacity) {
			newQueue[j] = queue[i]; 
			j++;
		}
		newQueue[j] = queue[i];
		
		front = 0;
		rear = size(); 
		capacity = capacity >> 1;
		queue = newQueue;
	}
	
}
