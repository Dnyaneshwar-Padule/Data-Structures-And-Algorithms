package com.tca;

import com.tca.util.ExpressionTree;

public class App {

	public static void main(String[] args) throws Exception {
		
		String postfixExpression = "AB+CD-+";
	
		ExpressionTree expressionTree = new ExpressionTree();
		expressionTree.createExpressionTree(postfixExpression);
		
		expressionTree.inorder();
		System.out.println();
	

		expressionTree.preorder();
		System.out.println();

		expressionTree.postorder();
		System.out.println();
		
	}

}
