package com.tca.util;

import java.util.Iterator;

public class MyLinkedList implements Iterable<Integer>{

	private ListNode head;
	private int size;
	
	public MyLinkedList() {
		head = null;
		size = 0;
	}
	
	private class ListNode{
		Integer data;
		ListNode next;
		
		ListNode(Integer data, ListNode next){
			this.data = data;
			this.next = next;
		}
	}
	
	public void add(Integer data) {
		head = new ListNode(data, head);
		size++;
	}
	
	public int size() {
		return size;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new LinkedListIterator(head);
	}
	
	private class LinkedListIterator implements Iterator<Integer>{
		ListNode current;
		
		public LinkedListIterator(ListNode head) {
			current = head;
		}
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Integer next() {
			int data =  current.data;
			current = current.next;
			return data;
		}
		
	}
	
}
