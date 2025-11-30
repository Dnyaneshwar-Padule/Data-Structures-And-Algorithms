package com.tca.util;

import java.util.Random;

// T is key, U is value
public class MySkipList <T extends Comparable<T>, U>{

	// List Node
	private class ListNode{
		T key;
		U value;
		long level;
		ListNode next;
		ListNode down;
		
		ListNode(T key, U value, long level, ListNode next, ListNode down) {
			super();
			this.key = key;
			this.value = value;
			this.level = level;
			this.next = next;
			this.down = down;
		}		
	}
	
	
	private ListNode head;
	private long size;  // total elements in the skip list
	private double _p;   // probability no.
	private Random _random; // to generate random level

	public MySkipList() {
		head = new ListNode(null, null, 0, null, null);
		size = 0;
		_p = 0.5;
		_random = new Random();
	}
	
	private long level() {
		long level = 0;
		while(level <= size && _random.nextDouble() < _p)
			level++;
		return level;
	}
	
	public long size() {
		return size;
	}
	
	public void clear() {
		size = 0;

		// Garbage Collector will take care.... :)
		head = new ListNode(null, null, 0, null, null);
	}
	
	public void add(T key, U value) {
		long level = level();
		
		// If generated level is grater than level of head, then increase level of head
		if(level > head.level) {			
			head = new ListNode(null, null, level, null, head);
		}
		
		ListNode current = head;
		ListNode last = null;  // last is used to link ListNode's down node, last means upper node
		
		while(current != null) {
			if(current.next == null || current.next.key.compareTo(key) > 0) {
				if(level >= current.level) {
					ListNode newNode = new ListNode(key, value, current.level, current.next, null);
					
					if(last != null)
						last.down = newNode;
					
					current.next = newNode;
					last = newNode;
				}
				current = current.down;
				continue;
			}
			else if(current.next.key.equals(key)) {
				current.next.key = key;
				return;
			}
			current = current.next;
		}
		
		size++;
	}
	
	public U remove(T key) {
		U value = null;
		ListNode current = head;
		boolean found = false;
		
		while(current != null) {
			if(current.next == null || current.next.key.compareTo(key) >= 0) {
				
				if(current.next != null && current.next.key.equals(key)) {
					value = current.next.value;
					current.next = current.next.next;
					found = true;
				}
				current = current.down;
				continue;
			}
			current = current.next;
		}
		
		if(found) size--;
		return value;
	}
	
	public U get(T key) {
		ListNode current = head;
		
		while(current != null) {
			if(current.next == null || current.next.key.compareTo(key) > 0) {
				current = current.down;
			}
			else if (current.next.key.equals(key)) {
				return current.next.value;
			}
			else
				current = current.next;
		}
		return null;
	}
	
	public boolean contains(T key) {
		return get(key) != null;
	}
	
	
		
}
