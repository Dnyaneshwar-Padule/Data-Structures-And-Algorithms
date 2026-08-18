package com.tca.util;

import java.util.Stack;

public class ExpressionTree {

	/*
	 * A single node in the tree
	 */
	private class TreeNode{
		char data;
		TreeNode left;
		TreeNode right;
	}
	
	private TreeNode root;
	
	
	public void createExpressionTree(String postfixExpression) throws Exception{
		if(postfixExpression == null || postfixExpression.isBlank())
			throw new IllegalArgumentException("Need postfix expression to create expression tree.");
	
		Stack<TreeNode> s = new Stack<>();
		
		try {
			for(char ch: postfixExpression.toCharArray()) {
				if(isOperator(ch)) {
					TreeNode operand2 = s.pop();
					TreeNode operand1 = s.pop();
					
					TreeNode operator = new TreeNode();
					operator.data = ch;
					operator.right = operand2;
					operator.left = operand1;
					s.push(operator);
				}
				else {
					TreeNode newNode = new TreeNode();
					newNode.data = ch;
					s.push(newNode);
				}
			}
			
			root = s.pop();
			System.out.println("Expression Tree created !!");
		}
		catch (Exception e) {
			throw new Exception("Invalid postfix expression.");
		}
	}
	
	private boolean isOperator(char ch) {
		return switch(ch) {
		case '+' -> true;
		case '-' -> true;
		case '*' -> true;
		case '/' -> true;
		case '%' -> true;
		default -> false;
		};
	}
	
	public void inorder() {
		traverseInorder(root);
	}
	
	private void traverseInorder(TreeNode root) {
		if(root == null)
				return;
		traverseInorder(root.left);
		System.out.print(root.data + " ");
		traverseInorder(root.right);
	}
	
	public void preorder() {
		traversePreorder(root);
	}
	
	private void traversePreorder(TreeNode root) {
		if(root == null)
				return;
		System.out.print(root.data + " ");
		traverseInorder(root.left);
		traverseInorder(root.right);
	}
	
	public void postorder() {
		traversePostorder(root);
	}
	
	private void traversePostorder(TreeNode root) {
		if(root == null)
				return;
		traverseInorder(root.left);
		traverseInorder(root.right);
		System.out.print(root.data + " ");
	}

}
