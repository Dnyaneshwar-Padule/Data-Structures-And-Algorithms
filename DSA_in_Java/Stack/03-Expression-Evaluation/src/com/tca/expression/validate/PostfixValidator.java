package com.tca.expression.validate;

import java.util.List;

import com.tca.parser.ValidationException;
import com.tca.token.NumberToken;
import com.tca.token.OperatorToken;
import com.tca.token.Token;
import com.tca.token.VariableToken;

public class PostfixValidator implements ExpressionValidator {

	@Override
	public void validate(List<Token> tokens) throws ValidationException {
		if(tokens == null || tokens.isEmpty())
			throw new ValidationException("Empty expression.");
		
		int operands = 0;
		
		for(Token token : tokens) {
			if(token instanceof NumberToken || token instanceof VariableToken)
				operands++;
			else if(token instanceof OperatorToken) {
				operands -= 2;
				if(operands < 0) {
					throw new ValidationException("Less number of operands in postfix expression.");
				}
				operands++;
			}
		}

		if(operands != 1)
			throw new ValidationException("Invalid postfix expression.");
		
	}

}
