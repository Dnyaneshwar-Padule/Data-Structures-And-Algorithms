package com.tca.expression.api;

import java.util.Map;

import com.tca.parser.ValidationException;

public interface Expression {

	Expression toInfix() throws ValidationException;
	Expression toPostfix() throws ValidationException;
	Expression toPrefix() throws ValidationException;
	double evaluate(Map<String, Double> vars);
	
}
