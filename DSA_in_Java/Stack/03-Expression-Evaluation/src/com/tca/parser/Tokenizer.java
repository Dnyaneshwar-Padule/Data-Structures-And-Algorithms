package com.tca.parser;

import java.util.ArrayList;
import java.util.List;

import com.tca.token.NumberToken;
import com.tca.token.OperatorToken;
import com.tca.token.OperatorToken.Associativity;
import com.tca.token.ParenthesisToken;
import com.tca.token.ParenthesisToken.Type;
import com.tca.token.Token;
import com.tca.token.VariableToken;

public class Tokenizer {

	public static List<Token> tokenize(String expression){
		List<Token> tokens = new ArrayList<>();
		String operators = "+-*/%";
		double num = 0;
		
		if(expression == null)
			return tokens;
		
		for( int i = 0; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			
			if(Character.isWhitespace(ch))
				continue;
			
			if(Character.isDigit(ch)) {
				int j;
				num = 0;
				for(j = i; j < expression.length(); j++) {
					ch = expression.charAt(j);
					if( Character.isDigit(ch) )
						num = (num * 10) + ch - '0';
					else 
						break;
				}
				tokens.add(new NumberToken( num ));
				i = j -1;
			}
			else if(operators.indexOf(ch) >= 0) {
				tokens.add(operatorFor(ch));
			}
			else if(ch == '(' || ch == ')') {
				tokens.add( ch == '(' ? 
						new ParenthesisToken(Type.LEFT) :
						new ParenthesisToken(Type.RIGHT));
			}
			else if (Character.isLetter(ch)){
				tokens.add( new VariableToken(String.valueOf(ch)) );
			}
			else {
				
				throw new IllegalArgumentException("Invalid character : " + ch);
			}
		}
		
		return tokens;
	}
	
	private static OperatorToken operatorFor(char ch) {
		//high number -> high priority
		return switch(ch) {
		case '+', '-' ->
			 new OperatorToken(ch, 1, Associativity.LEFT);
		case '*', '/', '%' ->
			 new OperatorToken(ch, 2, Associativity.LEFT);
		default ->
			throw new IllegalArgumentException("Invalid operator : " + ch);
		};
	}
	
}
