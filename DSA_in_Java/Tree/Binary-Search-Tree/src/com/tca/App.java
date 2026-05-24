package com.tca;

import java.util.ArrayList;

import com.tca.util.BinarySearchTree;

public class App {

	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		
		bst.insert(10);
		bst.insert(8);
		bst.insert(2);
		bst.insert(9);
		bst.insert(18);
		bst.insert(15);
		bst.insert(28);
		bst.insert(24);
		bst.insert(30);
		bst.insert(1);
		
		for(Integer num : bst.preorder()) {
			System.out.print(num + " ");
		}
		System.out.println();
		
		for(Integer num : bst.inorder()) {
			System.out.print(num + " ");
		}
		System.out.println();
		
		for(Integer num : bst.postorder()) {
			System.out.print(num + " ");
		}
		System.out.println();
		
		
		for(ArrayList<Integer> level : bst.levelOrder()) {
			for(Integer num : level)
				System.out.print(num + " ");
			System.out.println();
		}
		System.out.println();
		
		System.out.println("Search 15: " + bst.search(15) + " Level of 15: " + bst.levelOf(15));
		System.out.println("Search 10: " + bst.search(10) + " Level of 10: " + bst.levelOf(10));
		System.out.println("Search 1 : " + bst.search(1) + "  Level of 1: " + bst.levelOf(1));
		System.out.println("Search 30: " + bst.search(30) + " Level of 30: " + bst.levelOf(30));
		System.out.println("Search 50: " + bst.search(50) + " Level of 50: " + bst.levelOf(50));
		
		System.out.println("Max : " + bst.getMaxElement());
		System.out.println("Min : " + bst.getMinElement());
		
	}

}
