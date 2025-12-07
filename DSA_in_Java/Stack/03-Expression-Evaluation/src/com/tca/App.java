package com.tca;

import com.tca.expression.api.Expression;
import com.tca.parser.ExpressionParser;
import com.tca.parser.ValidationException;

public class App {

	public static void main(String[] args) throws ValidationException {
		
		
		Expression infix = ExpressionParser.parseInfix("(A+B)*C-D");
		System.out.println(infix.toString());
		System.out.println(infix.toPostfix().toString());
		System.out.println(infix.toPrefix().toString());
		System.out.println("_--------------------------_");
		
		Expression postfix = ExpressionParser.parsePostfix("AB+C*D-");
		System.out.println(postfix.toInfix().toString());
		System.out.println(postfix.toString());
		System.out.println(postfix.toPrefix().toString());

		System.out.println("_--------------------------_");
	
		Expression prefix = ExpressionParser.parsePrefix("-*+ABCD");
		System.out.println(prefix.toInfix().toString());
		System.out.println(prefix.toPostfix().toString());
		System.out.println(prefix.toString());
	
	}
}
