package com.tca;

import java.util.ArrayList;

import com.tca.util.BinaryTree;

public class App {

	public static void main(String[] args) {

		BinaryTree<Integer> t1 = new BinaryTree<>();
		BinaryTree<Integer> t2 = new BinaryTree<>();
		
		t1.insert(1);
		
		t1.insert(2);
		t1.insert(3);
		
		t1.insert(null);
		t1.insert(null);
		t1.insert(4);
		t1.insert(5);
		
		t1.insert(6);
		t1.insert(7);
		t1.insert(null);
		t1.insert(null);
		
		t1.insert(8);
		t1.insert(9);
			
		for(ArrayList<Integer> level : t1.levelOrder()) {
			for(Integer num : level)
				System.out.print(num + " ");
			System.out.println();
		}
		System.out.println();
		
		
		
	}

}
