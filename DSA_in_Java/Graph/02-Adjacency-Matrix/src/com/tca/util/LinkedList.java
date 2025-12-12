package com.tca.util;

import java.util.Iterator;

public class LinkedList<T> implements Iterable<Integer>{
	private ListNode<T> head;
	private int size;
	
	public LinkedList() {
		head = null;
		size = 0;
	}
	
	private class ListNode<T>{
		T data;
		ListNode<T> next;
		
		ListNode(T data, ListNode<T> next){
			this.data = data;
			this.next = next;
		}
		
	}

	public void add(T data) {
		head = new ListNode<T>(data, head);
		size++;
	}
	
	public int size() {
		return size;
	}
	
	public Iterator<Integer> iterator(){
		return new ListIterator(head);
	}
	
	private class ListIterator implements Iterator<Integer>{
		ListNode<Integer> current;
		
		@SuppressWarnings("unchecked")
		public ListIterator(ListNode head) {
			current = head;
		}
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Integer next() {
			Integer data = current.data;
			current = current.next;
			return data;
		}
		
	}
	
}
