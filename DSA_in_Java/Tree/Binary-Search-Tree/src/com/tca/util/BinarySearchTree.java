package com.tca.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {

	private class TreeNode{
		E data;
		TreeNode left;
		TreeNode right;
		
		TreeNode (E data){
			this.data = data;
		}
		
		TreeNode(E data, TreeNode left, TreeNode right){
			this.data = data;
			this.left = left;
			this.right = right;
		}
		
		TreeNode(TreeNode left, TreeNode right){
			this.left = left;
			this.right = right;
		}
	}
	
	
	private int size;
	private TreeNode root;
	
	public int size() {
		return size;
	}
	
	public void insert(E data) {	
		if(data == null)
			throw new IllegalArgumentException("Data can not be null.");
		
		if(root == null) {
			root = new TreeNode(data);
			size++;
			return;
		}
		
		TreeNode current = root;
		TreeNode previous = null;
		
		while(current != null) {
			previous = current;
			if(current.data.compareTo(data) == 0)
				return;
			else if (current.data.compareTo(data) > 0)
				current = current.left;
			else
				current = current.right;
		}

		if(previous.data.compareTo(data) > 0)
			previous.left = new TreeNode(data);
		else 
			previous.right = new TreeNode(data);
		
		size++;
	}
	

	public boolean search(E data) {
		if(data == null)
			throw new IllegalArgumentException("Data can not be null.");
	
		TreeNode current = root;
		
		while(current != null) {
			if(current.data.compareTo(data) == 0)
				return true;
			else if(current.data.compareTo(data) > 0 )
				current = current.left;
			else
				current = current.right;
		}
		
		return false;
	}
	
	
	public ArrayList<E> preorder(){
		ArrayList<E> result = new ArrayList<>();
		Stack<TreeNode> s = new Stack<>();
		
		if(root == null)
			return result;
		
		s.push(root);
		
		while( ! s.isEmpty() ) {
			TreeNode t = s.pop();
			
			if(t.right != null)
				s.push(t.right);
			
			if(t.left != null)
				s.push(t.left);
			
			result.add(t.data);
		
		}
		
		return result;
	}
	
  	public ArrayList<E> inorder(){
  		ArrayList<E> result = new ArrayList<>();
  		Stack<TreeNode> s = new Stack<>();
  		
  		if(root == null)
  			return result;
  	
  		TreeNode current = root;
  		boolean done = false;
  	
  		while(! done) {
  			if(current != null) {
  				s.push(current);
  				current = current.left;
  			}
  			else {
  				if(s.isEmpty())
  					done = true;
  				else {
  					current = s.pop();
  					result.add(current.data);
  					current = current.right;
  				}
  			}
  		}
  		
  		return result;  		
  	}
	
  	public ArrayList<E> postorder(){
  		ArrayList<E> result = new ArrayList<>();
  		Stack<TreeNode> s = new Stack<>();
  		
  		if(root == null)
  			return result;
  		
  		TreeNode previous = null;
  		s.push(root);
  		
  		while(! s.isEmpty()) {
  			TreeNode node = s.peek();
  			
  			if(previous == null || previous.left == node || previous.right == node) {
  				if(node.left != null)
  					s.push(node.left);
  				else if(node.right != null)
  						s.push(node.right);
  			}
  			else if(node.left == previous){
  				if(node.right != null)
  					s.push(node.right);
  			}
  			else {
  				s.pop();
  				result.add(node.data);
  			}
  			previous = node;
  		}
  		
  		return result;
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
  	
  	public int levelOf(E data){
  		int level = 0;
  		
  		if(data == null)
  			throw new IllegalArgumentException("Data can not be null.");
  		
  		if(root == null)
  			throw new IllegalStateException("BinarySearchTree is empty.");
  	
  		TreeNode cur = root;
  		
  		while(cur != null) {
  			if(cur.data.compareTo(data) == 0)
  				return level;
  			else if(cur.data.compareTo(data) > 0)
  				cur = cur.left;
  			else
  				cur = cur.right;
  			level++;
  		}
  	
  		return -1;
  	}
  	
  	public E getMaxElement() {
  		TreeNode cur = root;
  	
  		while(cur != null && cur.right != null)
  			cur = cur.right;
  		
  		return (cur == null) ? null : cur.data;
  	}
  	
  	public E getMinElement() {
  		TreeNode cur = root;
  		
  		while(cur != null && cur.left != null)
  			cur = cur.left;
  		
  		return (cur == null) ? null : cur.data;
  	}
  	
}

