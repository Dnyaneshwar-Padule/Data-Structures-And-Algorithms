package com.tca.util;

public class Queue {

	private Integer[] queue;
	private Integer rear;
	private Integer front;
	private Integer size;
	private Integer capacity;
	private static final Integer MAX_CAPACITY = 128;
	private static final Integer MIN_CAPACITY = 32;
	
	public Queue() {
		capacity = MIN_CAPACITY;
		queue = new Integer [capacity];
		rear = 0;
		front = 0;
		size = 0;
	}
	
	public Queue(Integer capacity) {
		this.capacity = (capacity > MAX_CAPACITY) ? MAX_CAPACITY : ( capacity < MIN_CAPACITY ? MIN_CAPACITY : capacity );
		queue = new Integer[this.capacity];
		rear = 0;
		front = 0;
		size = 0;
	}
	
	public int size() {
		return size;
	}
	
	public int capacity() {
		return capacity;
	}
	
	public void enQueue(Integer val) {
		if(size == capacity)
			throw new IllegalStateException("Queue is full: Overflow");
		
		size++;
		queue[rear] = val;
		rear = (rear + 1) % capacity;
	}
	
	
	public Integer deQueue() {
		if(size == 0)
			throw new IllegalStateException("Queue is empty: Underflow");
		
		size--;
		int val = queue[front];
		front = (front + 1) % capacity;
		return val;
	}
	
	public void clear() {
		size = 0;
	}
	
}
