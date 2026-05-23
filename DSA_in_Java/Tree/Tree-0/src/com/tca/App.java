package com.tca;

import java.util.ArrayList;
import java.util.List;

import com.tca.util.Tree;

public class App {

	public static void main(String[] args) {
		Tree<Integer> tree = new Tree();
		
		tree.insert(5);
		tree.insert(3);
		tree.insert(7);
		tree.insert(2);
		tree.insert(4);
		tree.insert(6);
		tree.insert(8);
		tree.insert(10);

		
		List<Integer> inOrder =  tree.inorder();
		
		for(int n : inOrder) {
			System.out.println(n);
		}
		System.out.println("\nLevel Order");
		
		ArrayList<ArrayList<Integer>> levels = tree.levelOrder();
		
		for(ArrayList<Integer> level : levels ) {
			for(Integer element: level) {
				System.out.print(element + " | ");
			}
			System.out.println();
		}
	}

}
