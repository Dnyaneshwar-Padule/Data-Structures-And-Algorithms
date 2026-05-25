package com.tca.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree <E extends Comparable<E>> {

	private class TreeNode{
		E data;
		TreeNode left;
		TreeNode right;
		
		TreeNode(E data){
			this.data = data;
		}
		
		TreeNode(E data, TreeNode left, TreeNode right){
			this.data = data;
			this.right = right;
			this.left = left;
		}
	}
	
	TreeNode root;
	int size;
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	public void insert(E data) {
		if(root == null) {
			root = new TreeNode(data);
		}
		else {
			Queue<TreeNode> q = new LinkedList<>();
			q.offer(root);
			
			while(! q.isEmpty()) {
				TreeNode node = q.poll();
				
				if(node.left == null) {
					if(node.data != null) {
						node.left = new TreeNode(data);
						break;						
					}
				}
				else {
					q.offer(node.left);
				}
				
				if(node.right == null) {
					if(node.data != null) {
						node.right = new TreeNode(data);
						break;						
					}
				}
				else {
					q.offer(node.right);
				}
			}
		}
		size++;
	}
	
	public boolean isSame(TreeNode anotherRoot) {
		boolean result = true;
		
		return result;
	}
	
	
	public ArrayList<ArrayList<E>> levelOrder(){
  		ArrayList<ArrayList<E>> levels = new ArrayList<>();
  		Queue<TreeNode> q = new LinkedList<>();
  		ArrayList<E> cur = new ArrayList<>();
  		
  		if(root == null)
  			return levels;
  
  		/*
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
  		*/
  		
  		q.offer(root);
  		while(! q.isEmpty()) {
  			ArrayList<E> level = new ArrayList<>();
  			int size = q.size();
  			
  			for(int i = 0; i < size; ++i) {
  				TreeNode node = q.poll();
  				if(node != null) {
  					level.add(node.data);
  					
  					if(node.left != null)
  						q.offer(node.left);
  					if(node.right != null)
  						q.offer(node.right);
  				}
  				
  			}
  			
  			levels.add(level);
  			
  		}
  		
  		return levels;
  	}
	
}
