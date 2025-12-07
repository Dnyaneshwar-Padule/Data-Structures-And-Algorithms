package com.tca.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tca.expression.api.Expression;
import com.tca.token.NumberToken;
import com.tca.token.OperatorToken;
import com.tca.token.ParenthesisToken;
import com.tca.token.ParenthesisToken.Type;
import com.tca.token.Token;
import com.tca.token.VariableToken;
import com.tca.util.Stack;

public class PostfixExpression implements Expression {
	List<Token> tokens;
	
	public PostfixExpression(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	@Override
	public Expression toInfix() {
		Stack<List<Token>> stack = new Stack<>();
 		
		try {
			
			for(Token token : tokens) {
				
				if(token instanceof NumberToken || token instanceof VariableToken) {
					stack.push( List.of(token) );
				}
				else {
					List<Token> right = stack.pop();
					List<Token> left = stack.pop();
					
					List<Token> expr = new ArrayList<Token>();
					//(left op right)
					expr.add( new ParenthesisToken(Type.LEFT) );
					expr.addAll(left);
					expr.add(token);
					expr.addAll(right);
					expr.add( new ParenthesisToken(Type.RIGHT) );
				
					stack.push(expr);
				}
			}
			
			return new InfixExpression(stack.pop());
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Expression toPostfix() {
		return this;
	}

	@Override
	public Expression toPrefix() {
		Stack<List<Token>> stack = new Stack<>();

		for (Token t : tokens) {

		    if (t instanceof NumberToken || t instanceof VariableToken) {
		        stack.push(List.of(t));
		    }
		    else {
		    	try {
		    		List<Token> right = stack.pop();
		    		List<Token> left  = stack.pop();
		    		List<Token> expr = new ArrayList<>();
		    		expr.add(t);
		    		expr.addAll(left);
		    		expr.addAll(right);
		    		stack.push(expr);
		    	}
		    	catch(Exception e) {
		    		e.printStackTrace();
		    	}
		    }
		}
		
		try {
			List<Token> prefix = stack.pop();
			return new PrefixExpression(prefix);			
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public double evaluate(Map<String, Double> vars) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		String expr = "Postfix expression: ";
		for(Token t: tokens) {
			if(t instanceof NumberToken) {
				expr += ( (NumberToken) t).value();
			}
			else if(t instanceof OperatorToken) {
				expr += ((OperatorToken) t).symbol();
			}
			else if(t instanceof VariableToken) {
				expr += ((VariableToken) t).name();
			}
		}
		
		return expr;
	}
	
}
