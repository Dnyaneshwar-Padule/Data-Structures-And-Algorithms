package com.tca.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tca.expression.api.Expression;
import com.tca.parser.ValidationException;
import com.tca.token.NumberToken;
import com.tca.token.OperatorToken;
import com.tca.token.ParenthesisToken;
import com.tca.token.Token;
import com.tca.token.VariableToken;
import com.tca.util.Stack;

public class InfixExpression implements Expression {

	private List<Token> tokens;
	
	public InfixExpression(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	@Override
	public Expression toInfix() {
		return this;
	}

	@Override
	public Expression toPostfix() {
		List<Token> postfixTokens = new ArrayList<Token>();
		Stack<Token> stack = new Stack<>();
		
		try {
			for(Token token : tokens) {
				
				if(token instanceof NumberToken) {
					// Push operand
					postfixTokens.add(new NumberToken( ((NumberToken)token).value()));
				}
				else if(token instanceof VariableToken) {
					// push operand
					postfixTokens.add(new VariableToken( ((VariableToken)token).name() ));				
				}
				else if(token instanceof ParenthesisToken && ((ParenthesisToken) token).isRight()) {
					//If we encounter a right parenthesis, pop operators (and add in postfixTokens) until we get left parenthesis
					Token t;
					while( ! stack.isEmpty() && !((t = stack.pop()) instanceof ParenthesisToken) )
						postfixTokens.add( new OperatorToken(
								((OperatorToken)t).symbol(),
								((OperatorToken)t).precedence(),
								((OperatorToken)t).associativity() ));
					
				}
				else {
					//if it's a parenthesis, it's left parenthesis, then push it to the stack
					if(token instanceof ParenthesisToken)
						stack.push(token);
					else {
						//else it's a operand
						//Then check the stack and pop operands with higher priority
						while(! stack.isEmpty() && stack.peek() instanceof OperatorToken
								&& ( (OperatorToken)stack.peek()).precedence() >= ( (OperatorToken)token).precedence() ) {
							Token t = stack.pop();
							postfixTokens.add( new OperatorToken(
									((OperatorToken)t).symbol(),
									((OperatorToken)t).precedence(),
									((OperatorToken)t).associativity() ));
						}
						stack.push(token);
						
					}
				}
				
			}
			
			while(! stack.isEmpty()  ) {
				Token t = stack.pop();
				postfixTokens.add( new OperatorToken(
						((OperatorToken)t).symbol(),
						((OperatorToken)t).precedence(),
						((OperatorToken)t).associativity() ));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return new PostfixExpression(postfixTokens);
	}

	@Override
	public Expression toPrefix() {
		try {
			return  toPostfix().toPrefix();
		} catch (ValidationException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public double evaluate(Map<String, Double> vars) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString() {
		String expr = "Infix expression: ";
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
			else {
				expr += ((ParenthesisToken)t).isLeft() ? "(" : ")";
			}
		}
		
		return expr;
	}

}
