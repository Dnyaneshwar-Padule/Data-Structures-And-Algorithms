package com.tca.parser;

import java.util.List;

import com.tca.expression.api.Expression;
import com.tca.expression.validate.InfixValidator;
import com.tca.expression.validate.PostfixValidator;
import com.tca.expression.validate.PrefixValidator;
import com.tca.impl.InfixExpression;
import com.tca.impl.PostfixExpression;
import com.tca.impl.PrefixExpression;
import com.tca.token.Token;

public final class ExpressionParser {

    public static Expression parseInfix(String input) throws ValidationException {
    	List<Token> tokens = Tokenizer.tokenize(input);
        new InfixValidator().validate(tokens);
        return new InfixExpression(tokens);
    }

    public static Expression parsePostfix(String input) throws ValidationException{
    	List<Token> tokens = Tokenizer.tokenize(input);
        new PostfixValidator().validate(tokens);
        return new PostfixExpression(tokens);
    }

    public static Expression parsePrefix(String input) throws ValidationException{
    	List<Token> tokens = Tokenizer.tokenize(input);
    	new PrefixValidator().validate(tokens);
    	return new PrefixExpression(tokens);
    }
	
}
