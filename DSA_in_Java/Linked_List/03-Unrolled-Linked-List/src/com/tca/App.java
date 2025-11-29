package com.tca;

import java.util.ListIterator;

import com.tca.util.MyUnrolledLinkedList;

public class App {

	public static void main(String[] args) {
		
		MyUnrolledLinkedList<Integer> myUll = new MyUnrolledLinkedList<Integer>();
		
		myUll.add(1);
		myUll.add(2);
		myUll.add(3);
		myUll.add(4);
		myUll.add(5);
		myUll.add(6);
		myUll.add(7);
		myUll.add(8);
		myUll.add(9);
		myUll.add(10);
		
		ListIterator<Integer> iterator = myUll.iterator();
		
		System.out.println("Forward...");
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		System.out.println("Backward...");
		while(iterator.hasPrevious()) {
			System.out.println(iterator.previous());
		}
		
		myUll.remove(1);
		
		
		myUll.clear();
		
	}
	
}
