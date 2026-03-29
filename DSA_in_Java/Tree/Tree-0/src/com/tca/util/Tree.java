package com.tca.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Tree <E extends Comparable<E>> {

	private TreeNode<E> root;
	
	@SuppressWarnings("unchecked")
	public boolean insert(E data) {
		if(data == null)
			throw new IllegalArgumentException("Data can not be null.");
		
		TreeNode<E> newNode = new TreeNode<>();
		newNode.setData(data);
		
		if(root == null) {
			root = newNode;
		}
		else {
			TreeNode<E> cur = root, prev = null;
			
			while(cur != null) {
				prev = cur;
				if(data.compareTo( cur.getData() ) < 0) {
					cur = cur.getLeft();
				}
				else if(data.compareTo(cur.getData()) == 0) {
					return false;
				}
				else {
					cur = cur.getRight();
				}
			}
			
			if(data.compareTo(prev.getData()) < 0) {
				prev.setLeft(newNode);
			}
			else {
				prev.setRight(newNode);
			}
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<E> inorder(){
		ArrayList<E> result = new ArrayList<>();
		Stack<TreeNode<E>> stack = new Stack<>();
		boolean isDone = false;
		TreeNode<E> current = root;
				
		while( ! isDone ) {
			if(current != null) {
				stack.push(current);
				current = current.getLeft();
			}
			else {
				if(stack.isEmpty()) {
					isDone = true;
				}
				else {
					current = stack.pop();
					result.add(current.getData());
					current = current.getRight();
				}
			}
		}
		return result;
	}
	
}
