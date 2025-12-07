package com.tca;

import java.util.List;

import com.tca.expression.validate.PostfixValidator;
import com.tca.parser.Tokenizer;
import com.tca.parser.ValidationException;
import com.tca.token.NumberToken;
import com.tca.token.OperatorToken;
import com.tca.token.Token;
import com.tca.util.Stack;

public class App {

	public static void main(String[] args) throws ValidationException {
		
		Stack<Integer> s = new Stack<Integer>();
		
		List<Token> tokens  = Tokenizer.tokenize("12+3-");
		
		for(Token t : tokens) {
			if(t instanceof NumberToken)
				System.out.println(((NumberToken) t).value());
			if(t instanceof OperatorToken)
				System.out.println(((OperatorToken) t).symbol());
		}
		
		new PostfixValidator().validate(tokens);
		
		
		try {
			for(int i = 1; i < 30; ++i)
				s.push(i);
			
			System.out.println("Peek : " + s.peek());
			
			for(int i = 1; i <= 30; ++i) {
				System.out.println("Pop : " + s.pop() + " size : " + s.size());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
