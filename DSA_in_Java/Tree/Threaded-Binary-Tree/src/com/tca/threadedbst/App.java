package com.tca.threadedbst;

import java.util.ArrayList;

public class App {

	public static void main(String[] args) {

		ThreadedBinarySearchTree<Integer> i = ThreadedBinarySearchTree.createInorderThreadedBinaryTree();
	
		i.insert(10);
//		i.insert(20);
//		i.insert(5);
//		i.insert(30);
//		i.insert(7);
//		i.insert(17);
//		i.insert(15);
//		i.insert(50);
//		i.insert(1);
//		i.insert(3);
		
		i.insert(50);
		i.insert(5);
		i.insert(40);
		i.insert(45);
		
		for(Integer val : i) {
			System.out.print(val + " - ");
		}
		System.out.println();
		
		ArrayList<Integer> i1;
	}

}
