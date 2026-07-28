package com.tca.threadedbst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.tca.threadedbst.exception.DuplicateDataException;
import com.tca.threadedbst.node.ThreadedBSTNode;

//                       type uses extends for both class and interface
public class ThreadedBinarySearchTree<E extends Comparable<E>> {

	
	private ThreadedBSTNode<E> root;
	private ThreadedBSTNode<E> dummy;
	
	public ThreadedBinarySearchTree() {
		dummy = new ThreadedBSTNode<E>();
		dummy.setlTag(true);
		dummy.setrTag(true);
		dummy.setRight(dummy);
		dummy.setLeft(dummy);
	}
	
	public boolean search(E key) {
		ThreadedBSTNode<E> cur = root;
		
		while(cur != null) {
			if(cur.getData().compareTo(key) == 0)
				return true;
			else if(cur.getData().compareTo(key) < 0)
				cur = cur.getRight();
			else
				cur = cur.getLeft();
		}
		
		return false;
	}
	
	public void insert(E data) {
		if(data == null)
				throw new IllegalArgumentException("Data cannot be null !");
		
		ThreadedBSTNode<E> cur = root, previous = null;
		ThreadedBSTNode<E> newNode = new ThreadedBSTNode<E>(data);
		
		if(root == null) {
			root = newNode;
			
			// attach root threads to dummy
			root.setlTag(true);
			root.setLeft(dummy);
			
			root.setrTag(true);
			root.setRight(dummy);
				
			//attach root to dummy
			dummy.setLeft(root);
			dummy.setrTag(false); // left is no more a thread....
		}
		else {
			// insert the new node...
			cur = root;
			while(cur != null) {
				previous = cur;
				if(cur.getData().compareTo(data) == 0)
					return;
				else if (cur.getData().compareTo(data) > 0)
					cur = cur.getLeft();
				else
					cur = cur.getRight();
			}
//
//			if(previous.getData().compareTo(data) > 0)
//				previous.setLeft(newNode);
//			else 
//				previous.setRight(newNode);
			
		}
	}
	
	public List<E> inorder(){
		Stack<ThreadedBSTNode<E>> s = new Stack<>();
		ThreadedBSTNode<E> cur = root;
		boolean done = false;
		List<E> list = new ArrayList<E>();
		
		if(root == null)
  			return list;
		
		while(!done) {
			if(cur != null) {
				s.push(cur);
				cur = cur.getLeft();
			}
			else {
				if(s.isEmpty())	
					done = true;
				else {
					cur = s.pop();
					list.add(cur.getData());
					cur = cur.getRight();
				}
			}
		}
		return list;
	}
	
	public List<E> preorder(){
		Stack<ThreadedBSTNode<E>> s = new Stack<>();
		ThreadedBSTNode<E> cur;
		List<E> list = new ArrayList<E>();
		
		if(root == null)
  			return list;
		
		s.push(root);
		
		while(! s.isEmpty()) {
			cur = s.pop();
			list.add(cur.getData());
			
			if(cur.getRight() != null)
				s.push(cur.getRight());
			if(cur.getLeft() != null)
				s.push(cur.getLeft());
		}
		
		return list;
	}
	
	public List<E> postorder(){
		Stack<ThreadedBSTNode<E>> s = new Stack<>();
		List<E> list = new ArrayList<E>();
		ThreadedBSTNode<E>previous = null;	

		if(root == null)
  			return list;
		
		s.add(root);
		
		while(! s.isEmpty()) {
			ThreadedBSTNode<E> node = s.peek();
			
			if(previous == null || previous.getLeft() == node || previous.getRight() == node) {
				if(node.getLeft() != null)
					s.push(node.getLeft());
				else if(node.getRight() != null)
					s.push(node.getRight());
			}
			else if(node.getLeft() == previous) {
				if(node.getRight() != null)
					s.push(node.getRight());
			}
			else {
				list.add(s.pop().getData());
			}
			previous = node;
		}
		
		return list;
	}
	
	public ArrayList<ArrayList<E>> levelOrder(){
  		ArrayList<ArrayList<E>> levels = new ArrayList<>();
  		Queue<ThreadedBSTNode<E>> q = new LinkedList<>();
  		ArrayList<E> cur = new ArrayList<>();
  		
  		if(root == null)
  			return levels;
  
  		q.offer(root);
  		q.offer(null);
  		
  		while(! q.isEmpty()) {
  			ThreadedBSTNode<E> node = q.poll();
  			
  			if(node != null) {
  				cur.add(node.getData());

  				if(node.getLeft()!= null)
  					q.offer(node.getLeft());
  				if(node.getRight() != null)
  					q.offer(node.getRight());
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
