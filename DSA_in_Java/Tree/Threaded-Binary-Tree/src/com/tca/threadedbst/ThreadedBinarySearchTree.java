package com.tca.threadedbst;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.tca.threadedbst.context.InsertContext;
import com.tca.threadedbst.node.ThreadedBSTNode;
import com.tca.threadedbst.strategy.InorderThreadingStrategy;
import com.tca.threadedbst.strategy.PreorderThreadingStrategy;
import com.tca.threadedbst.strategy.ThreadingStrategy;

/*
                       type uses extends for both class and interface
*/
public class ThreadedBinarySearchTree<E extends Comparable<E>> implements Iterable<E> {

	private final ThreadingStrategy<E> threadingStrategy;
	private ThreadedBSTNode<E> dummy;
	private ThreadedBSTNode<E> root;
	
	/*
		If tag is true it means that the pointer is a thread pointing to predecessor or successor.
	*/
	private ThreadedBinarySearchTree(ThreadingStrategy<E> threadingStrategy) {
		this.threadingStrategy = threadingStrategy;
		dummy = new ThreadedBSTNode<E>();
		dummy.setlTag(false);
		dummy.setrTag(false);
		dummy.setRight(dummy);
		dummy.setLeft(dummy);
	}
	
	public static <E extends Comparable<E>> ThreadedBinarySearchTree<E> createInorderThreadedBinaryTree() {
		return new ThreadedBinarySearchTree<E>(new InorderThreadingStrategy<E>());
	}
	
	public static <E extends Comparable<E>> ThreadedBinarySearchTree<E> createPreorderThreadedBinaryTree(){
		return new ThreadedBinarySearchTree<E>(new PreorderThreadingStrategy<E>());
	}
	
	public boolean search(E key) {
		ThreadedBSTNode<E> cur = root;
		
		while(cur != null) {
			if(cur.getData().compareTo(key) == 0)
				return true;
			else if(cur.getData().compareTo(key) < 0)
				if(! cur.isrTag())
					cur = cur.getRight();
				else
					cur = null;
			else
				if(! cur.islTag())
					cur = cur.getLeft();
				else
					cur = null;
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
			
			// attach root's thread to dummy
			root.setlTag(true);
			root.setLeft(dummy);
			
			root.setrTag(true);
			root.setRight(dummy);
				
			//attach root to dummy
			dummy.setLeft(root);
			//dummy.setlTag(false); // left is no more a thread....
		}
		else {
			/*
			 	insert the new node...
				There are no null pointers in Threaded Binary Tree (in leaf nodes)
				So we should take care of it...
			*/
			cur = root;
			while(cur != null) {
				previous = cur;
				if(cur.getData().compareTo(data) == 0)
					return;
				else if (cur.getData().compareTo(data) > 0) {
					if( ! cur.islTag())
						cur = cur.getLeft();					
					else
						cur = null;
				}
				else {
					if(! cur.isrTag())
						cur = cur.getRight();
					else
						cur = null;
				}
			}

			
			
			boolean isLeftChild = false;
			
			if(previous.getData().compareTo(data) > 0) {				
				isLeftChild = true;
			}
			
			threadingStrategy.afterInsert(
						new InsertContext<E>(
									previous,
									newNode,
									isLeftChild
								)
					);
		}
	}
		
	

	public List<E> traverse(){
		return threadingStrategy.traverse(dummy);
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

  				if(! node.islTag())
  					q.offer(node.getLeft());
  				if( ! node.isrTag())
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

	@Override
	public Iterator<E> iterator() {
		return threadingStrategy.iterator(dummy);
	}
	
}
