package com.tca.util;

public class TreeNode <E extends Comparable<E>>{

	private E data;
	private TreeNode<E> left;
	private TreeNode<E> right;
	
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public TreeNode getLeft() {
		return left;
	}
	public void setLeft(TreeNode left) {
		this.left = left;
	}
	public TreeNode getRight() {
		return right;
	}
	public void setRight(TreeNode right) {
		this.right = right;
	}
	
}
