package com.tca.threadedbst.strategy;

import java.util.Iterator;
import java.util.List;

import com.tca.threadedbst.context.InsertContext;
import com.tca.threadedbst.node.ThreadedBSTNode;

public interface ThreadingStrategy<E>{
	
	public void afterInsert(InsertContext<E> insertContext);
	
	public List<E> traverse(ThreadedBSTNode<E> root);
	
	public Iterator<E> iterator(ThreadedBSTNode<E> dummy);
	
	/*
	 	need to keep either traverse() or iterator()
	 */
}
