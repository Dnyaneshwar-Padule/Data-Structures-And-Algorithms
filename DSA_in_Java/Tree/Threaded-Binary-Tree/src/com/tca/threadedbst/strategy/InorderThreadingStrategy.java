package com.tca.threadedbst.strategy;

import com.tca.threadedbst.context.InsertContext;
import com.tca.threadedbst.node.ThreadedBSTNode;

public class InorderThreadingStrategy<E> implements ThreadingStrategy<E> {

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
			
			//update parent's pointers and tage
			parent.setRight(newNode);
			parent.setrTag(false);
		}
	}

}
