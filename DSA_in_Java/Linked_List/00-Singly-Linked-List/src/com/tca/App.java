package com.tca;

import com.tca.util.MyLinkedList;

public class App {

	public static void main(String[] args) {
		
		MyLinkedList<Integer> list = new MyLinkedList<Integer>();
		
		list.insert(0, 0);
		list.insert(1, 1);
		list.insert(2, 2);
		list.insert(2, 2);
		list.insert(2, 2);
		list.insert(3, 3);
		list.insert(4, 4);
		list.insert(4, 4);
		list.insert(4, 4);
		list.insert(5, 5);

		list.insert(1, 6);
		list.insert(3, 7);
		list.insert(8, 8);
		list.insert(9, 9);
		
		System.out.println("Index --> Value");
		
		for(int  i = 0; i < list.size(); i++) {
			System.out.println(i +"    ---> " + list.get(i));
		}
		
		System.out.println("------------");
		
		list.removeDuplicates();
		
		for(int  i = 0; i < list.size(); i++) {
			System.out.println(i +"    ---> " + list.get(i));
		}
		
	}
	
}
