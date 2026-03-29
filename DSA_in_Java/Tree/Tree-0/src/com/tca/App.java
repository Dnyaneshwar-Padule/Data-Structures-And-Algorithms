package com.tca;

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
		
		List<Integer> inOrder =  tree.inorder();
		
		for(int n : inOrder) {
			System.out.println(n);
		}
	}

}
