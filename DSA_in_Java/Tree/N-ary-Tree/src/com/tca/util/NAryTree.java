package com.tca.util;

public class NAryTree <E extends Comparable<E>> {
	
	private class TreeNode{
		E data;
		TreeNode firstChild;
		TreeNode nextSibling;
		
		TreeNode (E data){
			this.data = data;
		}
		
		TreeNode(E data, TreeNode firstChild, TreeNode nextSibling){
			this.data = data; 
			this.firstChild = firstChild;
			this.nextSibling = nextSibling;
		}
	}
	
	
	private TreeNode root;
	private int size;
	
	public int size() {
		return size;
	}
	
	
	
	
}
