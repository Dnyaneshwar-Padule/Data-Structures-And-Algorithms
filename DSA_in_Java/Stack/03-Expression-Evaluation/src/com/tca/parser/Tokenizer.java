package com.tca.parser;

import java.util.ArrayList;
import java.util.List;

import com.tca.token.NumberToken;
import com.tca.token.OperatorToken;
import com.tca.token.OperatorToken.Associativity;
import com.tca.token.ParenthesisToken;
import com.tca.token.ParenthesisToken.Type;
import com.tca.token.Token;

public class Tokenizer {

	public static List<Token> tokenize(String expression){
		List<Token> tokens = new ArrayList<>();
		String operators = "+-*/%";
		
		if(expression == null)
			return tokens;
		
		for( char ch : expression.toCharArray()) {
			
			if(Character.isWhitespace(ch))
				continue;
			
			if(Character.isDigit(ch)) {
				tokens.add(new NumberToken( ch - '0' ));
			}
			else if(operators.indexOf(ch) >= 0) {
				tokens.add(operatorFor(ch));
			}
			else if(ch == '(' || ch == ')') {
				tokens.add( ch == '(' ? 
						new ParenthesisToken(Type.LEFT) :
						new ParenthesisToken(Type.RIGHT));
			}
			else {
				throw new IllegalArgumentException("Invalid character : " + ch);
			}
		}
		
		return tokens;
	}
	
	private static OperatorToken operatorFor(char ch) {
		return switch(ch) {
		case '+', '-' ->
			 new OperatorToken(ch, 1, Associativity.RIGHT);
		case '*', '/', '%' ->
			 new OperatorToken(ch, 2, Associativity.LEFT);
		default ->
			throw new IllegalArgumentException("Invalid operator : " + ch);
		};
	}
	
}
