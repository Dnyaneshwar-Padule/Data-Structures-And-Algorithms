package com.tca.threadedbst.node;

public class ThreadedBSTNode<E>{
	
	private E data;
	private ThreadedBSTNode<E> left;
	private ThreadedBSTNode<E> right;
	private boolean lTag;
	private boolean rTag;
	
	public ThreadedBSTNode() {}
	
	public ThreadedBSTNode(E data) {
		this.data = data;
	}

	public ThreadedBSTNode(E data, ThreadedBSTNode<E> left, ThreadedBSTNode<E> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public ThreadedBSTNode(E data, ThreadedBSTNode<E> left, ThreadedBSTNode<E> right, boolean lTag, boolean rTag) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
		this.lTag = lTag;
		this.rTag = rTag;
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public ThreadedBSTNode<E> getLeft() {
		return left;
	}

	public void setLeft(ThreadedBSTNode<E> left) {
		this.left = left;
	}

	public ThreadedBSTNode<E> getRight() {
		return right;
	}

	public void setRight(ThreadedBSTNode<E> right) {
		this.right = right;
	}

	public boolean islTag() {
		return lTag;
	}

	public void setlTag(boolean lTag) {
		this.lTag = lTag;
	}

	public boolean isrTag() {
		return rTag;
	}

	public void setrTag(boolean rTag) {
		this.rTag = rTag;
	}
	
	
	
}
