package com.tca.expression.validate;

import java.util.List;

import com.tca.parser.ValidationException;
import com.tca.token.NumberToken;
import com.tca.token.OperatorToken;
import com.tca.token.Token;
import com.tca.token.VariableToken;

public class PrefixValidator implements ExpressionValidator {

	@Override
	public void validate(List<Token> tokens) throws ValidationException {
		if(tokens == null || tokens.isEmpty())
			throw new ValidationException("Empty expression.");
		
		int need = 1;
		int len = tokens.size();
		
		for(int i = 0; i < len; ++i) {
			Token t = tokens.get(i);
		
			if(t instanceof NumberToken || t instanceof VariableToken)
				need--;
			else if(t instanceof OperatorToken)
				need++;
			else
				throw new ValidationException("Invalid token in expression.");				
			
			if(need < 0)
				throw new ValidationException("Incomplete prefix expression");
		}
		
		if(need != 0) {
			throw new ValidationException("Invalid prefix expression");
		}
		
	}

}
