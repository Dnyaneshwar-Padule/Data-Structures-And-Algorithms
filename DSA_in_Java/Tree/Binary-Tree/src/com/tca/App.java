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


		
		t2.insert(1);
		
		t2.insert(2);
		t2.insert(3);
		
		t2.insert(null);
		t2.insert(null);
		t2.insert(4);
		t2.insert(5);
		
		t2.insert(6);
		t2.insert(7);
		t2.insert(null);
		t2.insert(null);
		
		t2.insert(8);
		t2.insert(9);

		
		System.out.println(t1.areStructurallySimilar(t2));
		
		
		System.out.println(t1.width(0));
		System.out.println(t1.width(1));
		System.out.println(t1.width(2));
		System.out.println(t1.width(3));
		
		
		t1.printPathToLeafNodes();
		
//		for(ArrayList<Integer> level : t1.levelOrder()) {
//			for(Integer num : level)
//				System.out.print(num + " ");
//			System.out.println();
//		}
//		System.out.println();
//		
//	
//		t1.delete(4);
//		
//		for(ArrayList<Integer> level : t1.levelOrder()) {
//			for(Integer num : level)
//				System.out.print(num + " ");
//			System.out.println();
//		}
//		System.out.println();
//		
//		t1.delete(3);
//		
//		
//		for(ArrayList<Integer> level : t1.levelOrder()) {
//			for(Integer num : level)
//				System.out.print(num + " ");
//			System.out.println();
//		}
//		System.out.println();
//		
	}
	
	  private static boolean isPalindrome(ArrayList<Integer> list){
	        int i = 0, j = list.size() - 1;

	        while(i < j){
	            if(list.get(i++) != list.get(j--))
	                return false;
	        }

	        return true;
	    }

}
