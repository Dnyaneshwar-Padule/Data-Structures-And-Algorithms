package com.tca;

import java.util.HashMap;

import com.tca.expression.api.Expression;
import com.tca.expression.eval.EvaluationException;
import com.tca.parser.ExpressionParser;
import com.tca.parser.ValidationException;

public class App {

	public static void main(String[] args) throws ValidationException, EvaluationException {
		
		HashMap<String, Double> vars = new HashMap<>();
		vars.put("A", 125.0);
		vars.put("B", 150.0);
		vars.put("C", 175.0);
		vars.put("D", 225.0);
		
		Expression infix = ExpressionParser.parseInfix("(A + B ) * C - D ");
		System.out.println(infix.toString(vars));
		System.out.println("Expression Evaluation: " + infix.evaluate(vars));
		System.out.println("---------------------------------");
		
		Expression expr = ExpressionParser.parseInfix("123 * 1323 - 331 + 331 / 31 * 32");
		System.out.println(expr.toPostfix().toInfix().toString());
		System.out.println("Expression Evaluation: " + expr.evaluate(vars));
		System.out.println("---------------------------------");

		Expression expr2 = ExpressionParser.parsePostfix("AB+C*D-");
		System.out.println(expr2.toString(vars));
		System.out.println(expr2.toInfix().toString(vars));
		System.out.println("Expression Evaluation:" + expr2.evaluate(vars));
		
	}
} 
