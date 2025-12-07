package com.tca.expression.validate;

import java.util.List;

import com.tca.parser.ValidationException;
import com.tca.token.OperatorToken;
import com.tca.token.ParenthesisToken;
import com.tca.token.Token;

public class InfixValidator implements ExpressionValidator {

	@Override
	public void validate(List<Token> tokens) throws ValidationException {
		if(tokens == null || tokens.isEmpty())
			throw new ValidationException("Empty expression.");
		
		int parenthesis = 0;
		Token previous = null;
		
		/*
		  Infix expression : (A + B) * (A - B) + C
		 */
		
		for(Token token : tokens) {
			
			if(token instanceof ParenthesisToken) {
				if(((ParenthesisToken) token).isLeft())
					parenthesis++;
				else
					parenthesis--;
				
				if(parenthesis < 0)
					throw new ValidationException("Mismatched parenthesis.");
			}
			else if(token instanceof OperatorToken) {
				if(previous == null || previous instanceof OperatorToken ) {
					 throw new ValidationException("Invalid operator placement");
				}
			}
			previous = token;
		}
		
		if(parenthesis != 0)
			throw new ValidationException("Unbalanced parenthesis.");
		if(previous instanceof OperatorToken)
			throw new ValidationException("Expression ends with operator");
	}

}
