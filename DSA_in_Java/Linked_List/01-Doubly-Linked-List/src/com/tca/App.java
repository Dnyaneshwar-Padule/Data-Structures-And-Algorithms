package com.tca;

import com.tca.util.MyDoublyLinkedList;

public class App {
	
	public static void main(String[] args) {
		
		MyDoublyLinkedList<Integer> dll = new MyDoublyLinkedList<Integer>();
		
		dll.insert(1, 0);
		dll.insert(2, 1);
		dll.insert(3, 2);
		dll.insert(4, 3);
		dll.insert(5, 4);
		dll.insert(6, 5);
		dll.insert(7, 6);
		
		System.out.println("Index --> Value");
		for(int i = 0; i < dll.size(); i++)
			System.out.println(i + "    ---> " + dll.get(i));
		
		System.out.println("-----------");
		
		dll.remove(1);  //  2 removed
		dll.remove(2);  //  4 
		dll.remove(3);  //  6 
		dll.remove(4);  // 7 removed
		
		for(int i = 0; i < dll.size(); i++)
			System.out.println(i + "    ---> " + dll.get(i));
		
	}

}
