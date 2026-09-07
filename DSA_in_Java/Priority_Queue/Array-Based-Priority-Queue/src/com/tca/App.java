package com.tca;

import com.tca.util.Heap;
import com.tca.util.Heap.Type;

public class App {

	public static void main(String[] args)  {

		Heap minHeap = new Heap(0, Type.MIN_HEAP);

		for(int i = 16; i > 0; i--) {
			minHeap.insert(i);
			System.out.println(minHeap);
		}
		
		System.out.println(minHeap.size());
		
		for(int i = 1; i <= 16; i++) {
			System.out.println(minHeap.deleteMin() + " " + minHeap);
		}
		
	}

}
