package com.tca.util;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class Heap {

	public enum Type{
		MIN_HEAP,
		MAX_HEAP
	}
	
	private int heap[];
	private int capacity;
	private int size;
	private Type heapType;
	
	
	public Heap(int capacity, Heap.Type heapType) {
		if(capacity <= 0)
			capacity = 16;
		
		if(heapType == null)
				throw new IllegalArgumentException("Heap Type is required.");
		
		this.heapType = heapType;
		this.capacity = capacity;
		heap = new int[capacity];
	}
	
	private int parentOf(int i) {
		if(i <= 0 || i >= size)
				return -1;
		return (i - 1) / 2;
	}
	
	private int leftChild(int parentIndex) {
		if(parentIndex < 0 || parentIndex >= size)
			return -1;
		int child = (parentIndex * 2) + 1;
		if(child >= size)
			return -1;
		return child;
	}
	
	private int rightChild(int parentIndex) {
		if(parentIndex < 0 || parentIndex >= size)
			return -1;
		int child =  (parentIndex * 2) + 2;
		if(child >= size)
			return -1;
		return child;
	}
	
	private void percolateDown(int index) {
		if(index < 0 || index >= size)
				return;
		int leftChild, rightChild;
		leftChild = leftChild(index);
		rightChild = rightChild(index);
		
		if(heapType == Type.MAX_HEAP) {
			int max;
			
			if(leftChild != -1 && heap[leftChild] > heap[index])
				max = leftChild;
			else
				max = index;
			
			if(rightChild != -1 && heap[rightChild] > heap[max])
				max = rightChild;
			
			if(max != index) {
				int temp = heap[index];
				heap[index] = heap[max];
				heap[max] = temp;
				percolateDown(max);
			}
		}
		else {
			int min;
			
			if(leftChild != -1 && heap[leftChild] < heap[index])
				min = leftChild;
			else
				min = index;
			
			if(rightChild != -1 && heap[rightChild] < heap[min])
				min = rightChild;
			
			if(min != index) {
				int temp = heap[index];
				heap[index] = heap[min];
				heap[min] = temp;
				percolateDown(min);
			}
		}	
	}
	
	public int deleteMax() {
		if(heapType == Type.MIN_HEAP)
			throw new IllegalCallerException("Operation not supported by MIN_HEAP.");
		if(size == 0)
			throw new NoSuchElementException("Heap is empty.");
		int key = heap[0];
		heap[0] = heap[size - 1];
		size--;
		percolateDown(0);
		return key;
	}
	
	public int deleteMin() {
		if(heapType == Type.MAX_HEAP)
			throw new IllegalCallerException("Operation not supported by MAX_HEAP.");
		if(size == 0)
			throw new NoSuchElementException("Heap is empty.");
		int key = heap[0];
		heap[0] = heap[size - 1];
		size--;
		percolateDown(0);
		return key;
	}
	
	public  void insert(int data) {
		if(size == capacity)
			expandHeap();
		size++;
		int i = size - 1;
		
		if(heapType == Type.MAX_HEAP) {
			while(i >  0 && data > heap[parentOf(i)] ) {			
				heap[i] = heap[parentOf(i)];
				i = parentOf(i);
			}
		}
		else {
			while(i >  0 && data < heap[parentOf(i)] ) {			
				heap[i] = heap[parentOf(i)];
				i = parentOf(i);
			}
		}
		
		heap[i] = data;
	}
	
	private void expandHeap() {
		this.capacity = capacity * 2;
		int[] newHeap = new int[capacity];
		System.arraycopy(heap, 0, newHeap, 0, size);
		heap = newHeap;
	}
	
	
	public int size() {
		return size;
	}
	
	public int getMin() {
		if(heapType == Type.MAX_HEAP)
				throw new IllegalCallerException("Operation not supported by MAX_HEAP");
		if(size == 0)
			throw new NoSuchElementException("Heap is empty.");
		return heap[0];
	}
	
	public int getMax(){
		if(heapType == Type.MIN_HEAP)
			throw new IllegalCallerException("Operation not supported by MIN_HEAP");
		if(size == 0)
			throw new NoSuchElementException("Heap is empty.");
		return heap[0];
	}

	@Override
	public String toString() {
		return "Heap [heapType=" + heapType + ", heap=" + Arrays.toString(heap) +  "]";
	}
	
	
}
