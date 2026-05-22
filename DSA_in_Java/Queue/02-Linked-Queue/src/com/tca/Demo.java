package com.tca;

import com.tca.util.Queue;

public class Demo {

	public static void main(String[] args) {
		Queue<Integer> q = new Queue<>();
		
		q.enQueue(1);
		System.out.println(q.deQueue());
		q.enQueue(2);
		q.enQueue(3);
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
	
		q.enQueue(4);
		q.enQueue(5);
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		q.enQueue(6);
		q.enQueue(7);
		q.enQueue(8);
		q.enQueue(9);
		q.enQueue(10);
		q.enQueue(11);
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());		
	}

}
