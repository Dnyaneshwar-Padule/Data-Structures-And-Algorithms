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
		
//		for(Integer num : bst.preorder()) {
//			System.out.print(num + " ");
//		}
//		System.out.println();
//		
//		System.out.println("10->" + bst.preorderSuccessorOf(10));
//		System.out.println("8->" + bst.preorderSuccessorOf(8));
//		System.out.println("1 ->" + bst.preorderSuccessorOf(1));
//		System.out.println("18->" + bst.preorderSuccessorOf(18));
//		System.out.println("15->" + bst.preorderSuccessorOf(15));
//		System.out.println("24->" + bst.preorderSuccessorOf(24));	
//		System.out.println("30->" + bst.preorderSuccessorOf(30));
//		
//		System.out.println(bst.preorderPredessorOf(10) + "--> 10");
//		System.out.println(bst.preorderPredessorOf(8) + "--> 8");
//		System.out.println(bst.preorderPredessorOf(2) + "--> 2");
//		System.out.println(bst.preorderPredessorOf(30) + "--> 30");
//		System.out.println(bst.preorderPredessorOf(28) + "--> 28");
//		System.out.println(bst.preorderPredessorOf(18) + "--> 18");
//		System.out.println(bst.preorderPredessorOf(15) + "--> 15");
//		
		
		
		for(Integer num : bst.inorder()) {
			System.out.print(num + " ");
		}
		System.out.println();

		System.out.println("1-->" + bst.inorderSuccessorOf(1));
		System.out.println("2-->" + bst.inorderSuccessorOf(2));
		System.out.println("8-->" + bst.inorderSuccessorOf(8));
		System.out.println("9-->" + bst.inorderSuccessorOf(9));
		System.out.println("10-->" + bst.inorderSuccessorOf(10));
		System.out.println("15-->" + bst.inorderSuccessorOf(15));
		System.out.println("18-->" + bst.inorderSuccessorOf(18));
		System.out.println("24-->" + bst.inorderSuccessorOf(24));
		System.out.println("28-->" + bst.inorderSuccessorOf(28));
		System.out.println("30-->" + bst.inorderSuccessorOf(30));
		
		System.out.println("1--" + bst.inorderPredecessorOf(1));
		System.out.println("2--" + bst.inorderPredecessorOf(2));
		System.out.println("8--" + bst.inorderPredecessorOf(8));
		System.out.println("9--" + bst.inorderPredecessorOf(9));
		System.out.println("10--" + bst.inorderPredecessorOf(10));
		System.out.println("15--" + bst.inorderPredecessorOf(15));
		System.out.println("18--" + bst.inorderPredecessorOf(18));
		System.out.println("24--" + bst.inorderPredecessorOf(24));
		System.out.println("28--" + bst.inorderPredecessorOf(28));
		System.out.println("30--" + bst.inorderPredecessorOf(30));
		
//		for(Integer num : bst.postorder()) {
//			System.out.print(num + " ");
//		}
//		System.out.println();
//		
//		
//		for(ArrayList<Integer> level : bst.levelOrder()) {
//			for(Integer num : level)
//				System.out.print(num + " ");
//			System.out.println();
//		}
//		System.out.println();
//		
//		System.out.println("Search 15: " + bst.search(15) + " Level of 15: " + bst.levelOf(15));
//		System.out.println("Search 10: " + bst.search(10) + " Level of 10: " + bst.levelOf(10));
//		System.out.println("Search 1 : " + bst.search(1) + "  Level of 1: " + bst.levelOf(1));
//		System.out.println("Search 30: " + bst.search(30) + " Level of 30: " + bst.levelOf(30));
//		System.out.println("Search 50: " + bst.search(50) + " Level of 50: " + bst.levelOf(50));
//		
//		System.out.println("Max : " + bst.getMaxElement());
//		System.out.println("Min : " + bst.getMinElement());
		
	}

}
