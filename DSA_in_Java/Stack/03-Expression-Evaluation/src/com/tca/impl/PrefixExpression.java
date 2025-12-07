package com.tca.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tca.expression.api.Expression;
import com.tca.parser.ValidationException;
import com.tca.token.NumberToken;
import com.tca.token.OperatorToken;
import com.tca.token.Token;
import com.tca.token.VariableToken;
import com.tca.util.Stack;

public class PrefixExpression implements Expression {

	private List<Token> tokens;

	public PrefixExpression(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	
	@Override
	public Expression toInfix() {
		try {
			return  toPostfix().toInfix();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Expression toPostfix() {
	    Stack<List<Token>> stack = new Stack<>();

	    try {
	    	for (int i = tokens.size() - 1; i >= 0; i--) {
	    		
	    		Token t = tokens.get(i);
	    		
	    		if (t instanceof NumberToken || t instanceof VariableToken) {
	    			stack.push(List.of(t));
	    		}
	    		else{
	    			
	    			List<Token> left  = stack.pop();
	    			List<Token> right = stack.pop();
	    			
	    			List<Token> postfix = new ArrayList<>();
	    			postfix.addAll(left);
	    			postfix.addAll(right);
	    			postfix.add(t);
	    			
	    			stack.push(postfix);
	    		}
	    	}
	    	return new PostfixExpression(stack.pop());	    	
	    }
	    catch(Exception e) {
	    	e.printStackTrace();
	    	return null;
	    }
	}

	@Override
	public Expression toPrefix() {
		return this;
	}

	@Override
	public double evaluate(Map<String, Double> vars) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString() {
		String expr = "Prefix expression: ";
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
