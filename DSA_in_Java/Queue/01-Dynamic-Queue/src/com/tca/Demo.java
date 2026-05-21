package com.tca;

import com.tca.util.Queue;

public class Demo {

	public static void main(String[] args) {
		Queue q = new Queue(3);
		
		q.enQueue(0);
		q.enQueue(1);
		q.enQueue(2);
		
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		
		q.enQueue(3);
		q.enQueue(4);
		
		
		q.enQueue(5);
		q.enQueue(6);
		q.enQueue(7);
		
		q.enQueue(3);
		q.enQueue(4);
		
		
		q.enQueue(5);
		q.enQueue(6);
		q.enQueue(7);
		q.enQueue(3);
		q.enQueue(4);
		
		
		q.enQueue(5);
		q.enQueue(6);
		q.enQueue(7);
		q.enQueue(3);
		q.enQueue(4);
		
		
		q.enQueue(5);
		q.enQueue(6);
		q.enQueue(7);
		q.enQueue(3);
		q.enQueue(4);
		
		
		q.enQueue(5);
		q.enQueue(6);
		q.enQueue(7);
		q.enQueue(3);
		q.enQueue(4);
		
		
		q.enQueue(5);
		q.enQueue(6);
		q.enQueue(7);
		
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());

		
	}

}
