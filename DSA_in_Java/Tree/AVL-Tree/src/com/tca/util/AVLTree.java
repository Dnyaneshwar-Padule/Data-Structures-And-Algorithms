package com.tca.util;

public class AVLTree<E extends Comparable<E>> {
	
	@SuppressWarnings("unused")
	private class TreeNode{
		E data;
		TreeNode left;
		TreeNode right;
		long height;
		
		TreeNode(E data){
			this.data = data;
		}
	}
	
	private TreeNode root;
	int size;
	
	private long height(TreeNode root) {
		return root == null ? -1 : root.height;
	}
	
	/**
	 * LL Rotation
	 * @param x
	 * @return
	 */
	private TreeNode singleRotateLeft(TreeNode x) {
		TreeNode w = x.left;
		x.left = w.right;
		w.right = x;
		
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		w.height = Math.max(height(w.left), x.height) + 1;
		return w;
	}
	
	/**
	 * RR Rotation
	 * @param x
	 * @return
	 */
	private TreeNode singleRotateRight(TreeNode x) {
		TreeNode w = x.right;
		x.right = w.left;
		w.left = x;
		
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		w.height = Math.max(x.height, height(w.right)) + 1;
		return w;
	}
	
	/**
	 * LR Rotation
	 * @param z
	 * @return
	 */
	private TreeNode doubleRotateWithLeft(TreeNode z) {
		z.left = singleRotateRight(z.left);
		return singleRotateLeft(z);
	}
	
	/**
	 * RL Rotation
	 * @param z
	 * @return
	 */
	private TreeNode doubleRotateWithRight(TreeNode z) {
		z.right = singleRotateLeft(z.right);
		return singleRotateRight(z);
	}
}
