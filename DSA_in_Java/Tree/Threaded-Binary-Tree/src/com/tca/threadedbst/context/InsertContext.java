package com.tca.threadedbst.context;

import com.tca.threadedbst.node.ThreadedBSTNode;

public record InsertContext<E>(
	ThreadedBSTNode<E> parent,
	ThreadedBSTNode<E> newNode,
	boolean isLeftChild,
	ThreadedBSTNode<E> dummy
) {}
