package com.tca.parser;

import java.util.List;

import com.tca.expression.api.Expression;
import com.tca.expression.validate.InfixValidator;
import com.tca.impl.InfixExpression;
import com.tca.impl.PostfixExpression;
import com.tca.token.Token;

public final class ExpressionParser {

    public static Expression parseInfix(String input) throws ValidationException {
    	List<Token> tokens = Tokenizer.tokenize(input);
        new InfixValidator().validate(tokens);
        //return new InfixExpression(tokens);
		return null;
    }

    public static Expression parsePostfix(String input) {
        validatePostfix(input);
        return new PostfixExpression(Tokenizer.tokenize(input));
    }
	
}
