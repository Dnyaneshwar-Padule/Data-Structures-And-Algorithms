package com.tca;

import com.tca.util.StackUtil;

public class App {

	public static void main(String[] args) {
		
		String expr = "a+b*c-d-(1+2*3)";
		
		StackUtil.infixToPostfix(expr);
		
	}

}
