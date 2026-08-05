package com.tca.threadedbst.strategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import com.tca.threadedbst.context.InsertContext;
import com.tca.threadedbst.node.ThreadedBSTNode;

public class InorderThreadingStrategy<E> implements ThreadingStrategy<E> {

	/*
	 	Iterator to iterate the threaded tree in inorder
	 */
	private class InorderThreadedTreeIterator implements Iterator<E>{
		
		private final ThreadedBSTNode<E> dummy;
		private ThreadedBSTNode<E> current;
		
		InorderThreadedTreeIterator(ThreadedBSTNode<E> dummy) {
			this.dummy = dummy;
			current = getSuccessor(dummy);
		}
		
		@Override
		public boolean hasNext() {
			return current != dummy;
		}

		@Override
		public E next() {
			E data = current.getData();
			
			if(current == dummy)
				throw new NoSuchElementException();
			
			current = getSuccessor(current); // move current to next position
			return data;
		}
		
	}
	
	@Override
	public void afterInsert(InsertContext<E> insertContext) {
		// the new node and parent aren't still linked....
		ThreadedBSTNode<E> newNode = insertContext.newNode();
		ThreadedBSTNode<E> parent = insertContext.parent();

		//newNode's left and right both will be tags
		newNode.setlTag(true);
		newNode.setrTag(true);
		
		if(insertContext.isLeftChild()) {			
			newNode.setRight(parent);
			newNode.setLeft(parent.getLeft());
			
			// update parent's pointers and tags
			parent.setLeft(newNode);
			parent.setlTag(false);
		}
		else {
			newNode.setLeft(parent);
			newNode.setRight(parent.getRight());
			
			//update parent's pointers and tag
			parent.setRight(newNode);
			parent.setrTag(false);
		}
	}

	@Override
	public List<E> traverse(ThreadedBSTNode<E> root) {
		ThreadedBSTNode<E> cur = root;
		List<E> l = new ArrayList<>();
		
		if(root == null)
			return l;
			
		while(true) {
			cur = getSuccessor(cur);
			
			if(cur == root)
				break;
			
			l.add(cur.getData());
		}
		
		return l;
	}
	
	private ThreadedBSTNode<E> getSuccessor(ThreadedBSTNode<E> root){
		if(root.isrTag())
			return root.getRight();
		else {
			ThreadedBSTNode<E> position = root.getRight();
			while(! position.islTag())
				position = position.getLeft();
			return position;
		}
	}

	@Override
	public Iterator<E> iterator(ThreadedBSTNode<E> dummy) {
		return new InorderThreadedTreeIterator(dummy);
	}

}
