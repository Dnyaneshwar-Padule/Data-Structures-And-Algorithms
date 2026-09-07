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
		
		avlBst.delete(5);
		System.out.println("Del 5");
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
		
		avlBst.delete(1);
		System.out.println("Del 1");
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
		
		avlBst.delete(7);
		System.out.println("Del 7");
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
		
		avlBst.delete(4);
		System.out.println("Del 4");
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
