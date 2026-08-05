package com.tca.threadedbst.strategy;

import java.util.Iterator;
import java.util.List;

import com.tca.threadedbst.context.InsertContext;
import com.tca.threadedbst.node.ThreadedBSTNode;

public class PreorderThreadingStrategy<E> implements ThreadingStrategy<E> {

	@Override
	public void afterInsert(InsertContext<E> insertContext) {
		
	}

	@Override
	public List<E> traverse(ThreadedBSTNode<E> root) {
		return null;
	}

	@Override
	public Iterator<E> iterator(ThreadedBSTNode<E> dummy) {
		return null;
	}

}
