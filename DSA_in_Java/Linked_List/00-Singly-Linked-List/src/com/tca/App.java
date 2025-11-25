package com.tca;

import com.tca.util.MyLinkedList;

public class App {

	public static void main(String[] args) {
		
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		
		list.insert(0, 0);
		list.insert(1, 1);
		list.insert(2, 2);
		list.insert(3, 3);
		list.insert(4, 4);
		list.insert(5, 5);
		
		for(int  i = 0; i < list.size(); i++) {
			System.out.println(i +" ---> " + list.get(i));
		}
	}
	
}
