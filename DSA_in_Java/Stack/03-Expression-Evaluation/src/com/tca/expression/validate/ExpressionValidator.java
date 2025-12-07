package com.tca.expression.validate;

import java.util.List;

import com.tca.parser.ValidationException;
import com.tca.token.Token;

public interface ExpressionValidator {
	 void validate(List<Token> tokens) throws ValidationException;
}
