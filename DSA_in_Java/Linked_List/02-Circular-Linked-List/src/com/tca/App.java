package com.tca;

import com.tca.util.MyCircularLinkedList;

public class App {

	public static void main(String[] args) {
		MyCircularLinkedList<Integer> cll = new MyCircularLinkedList<Integer>();
		
		cll.add(1);
		cll.add(2);
		cll.add(3);
		cll.add(4);
		cll.add(5);
		
		System.out.println(cll);
		
		System.out.println("1 contains : " + cll.contains(1));
		System.out.println("3 contains : " + cll.contains(3));
		System.out.println("5 contains : " + cll.contains(5));
		System.out.println("7 contains : " + cll.contains(7));
		
		cll.remove(1);
		System.out.println(cll);
		cll.remove(3);
		System.out.println(cll);
		cll.remove(5);		
		System.out.println(cll);
		
	}
}
