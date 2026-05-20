package com.tca;

import com.tca.util.Queue;

public class Demo {

	public static void main(String[] args) {
		Queue queue = new Queue(5);
		
		System.out.println(queue.size() + " | " + queue.capacity());
		
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		queue.enQueue(5);
		queue.enQueue(6);
		queue.enQueue(7);
//		queue.enQueue(8);
		
		System.out.println(queue.size() + " | " + queue.capacity());
		
		
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		
		
	}

}
