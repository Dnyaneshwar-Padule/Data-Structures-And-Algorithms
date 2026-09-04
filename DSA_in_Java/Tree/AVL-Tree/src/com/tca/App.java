package com.tca;

import java.util.List;

import com.tca.util.AVLTree;

public class App {

	public static void main(String[] args) {
		
		AVLTree<Integer> avlBst = new AVLTree<Integer>();
		
		avlBst.insert(9);
		avlBst.insert(8);
		avlBst.insert(7);
		avlBst.insert(6);
		avlBst.insert(5);
		avlBst.insert(4);
		avlBst.insert(3);
		avlBst.insert(2);
		avlBst.insert(1);
		
		for(int val : avlBst.inorder()) {
			System.out.print(val + " - ");
		}
		System.out.println();
		
		
		for(List<Integer> level : avlBst.levelOrder()) {
			for(int val : level) {
				System.out.print(val + " - ");
			}
			System.out.println();
		}
		
	}

}
