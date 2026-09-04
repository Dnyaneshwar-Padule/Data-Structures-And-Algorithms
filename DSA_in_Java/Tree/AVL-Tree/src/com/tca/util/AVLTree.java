package com.tca.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	
	public void insert(E data) {
		if(data == null)
				throw new IllegalArgumentException("Data cannot be null.");
		root = insert(root, data);
	}
	
	private TreeNode insert(TreeNode root, E data) {
		if(root == null) {
			root = new TreeNode(data);
		}
		else if(data.compareTo(root.data) < 0) {  // data < root.data
			root.left = insert(root.left, data);
			if(height(root.left) - height(root.right) == 2) { // height imbalance, needs rotation
				if(data.compareTo(root.left.data) < 0) // data < root.left.data, i.e. SingleLeftRotation
					root = singleRotateLeft(root);
				else
					root = doubleRotateWithLeft(root);
			}	
		}
		else if(data.compareTo(root.data) > 0) {  // data > root.data
			root.right =  insert(root.right, data);
			if(height(root.right) - height(root.left) == 2) { // height imbalance, needs rotation
				if(data.compareTo(root.right.data) > 0 ) //data > root.right.data, i.e. SingleRightRotation 
					root = singleRotateRight(root);
				else
					root = doubleRotateWithRight(root);
			}
		}
		
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		return root;
	}
	
	public List<E> inorder(){
		List<E> traversal = new ArrayList<>();
		inorder(root, traversal);
		return traversal;
	}
	
	private void inorder(TreeNode root, List<E> traversal) {
		if(root == null)
			return;
		inorder(root.left, traversal);
		traversal.add(root.data);
		inorder(root.right, traversal);
	}
	
	public ArrayList<ArrayList<E>> levelOrder(){
  		ArrayList<ArrayList<E>> levels = new ArrayList<>();
  		Queue<TreeNode> q = new LinkedList<>();
  		ArrayList<E> cur = new ArrayList<>();
  		
  		if(root == null)
  			return levels;
  
  		q.offer(root);
  		q.offer(null);
  		
  		while(! q.isEmpty()) {
  			TreeNode node = q.poll();
  			
  			if(node != null) {
  				cur.add(node.data);

  				if(node.left != null)
  					q.offer(node.left);
  				if(node.right != null)
  					q.offer(node.right);
  			}
  			else {
  				ArrayList<E> cur_copy = new ArrayList<E>(cur);
  				levels.add(cur_copy);
  				cur.clear();
  				
  				if(! q.isEmpty())
  					q.offer(null);
  			}
  		}
  		
  		return levels;
  	}
}
