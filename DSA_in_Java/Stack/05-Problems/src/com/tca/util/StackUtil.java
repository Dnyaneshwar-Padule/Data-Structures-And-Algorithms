package com.tca.util;

import java.util.Stack;

public class StackUtil {

	public static String infixToPostfix(String expr) {
		Stack<Character> s = new Stack<>();
		StringBuilder result = new StringBuilder();
		
		for(Character ch: expr.toCharArray()) {
			if(Character.isLetter(ch) || Character.isDigit(ch)) {
				//System.out.print(ch);
				result.append(ch);
			}
			else if(ch.equals('(')) {
				s.push(ch);
			}
			else {
				if(ch.equals(')')) {
					while(! s.isEmpty() && ( ! s.peek().equals('(') ) ){
						//System.out.print(s.pop());
						result.append(s.pop());
					}
					s.pop();
				}
				else {
					while(! s.isEmpty() &&
						getPresedence(s.peek()) >= getPresedence(ch)) {
						//System.out.print(s.pop());
						result.append(s.pop());
					}
					s.push(ch);
				}
			}			
		}
		
		while(! s.isEmpty()) {
			//System.out.print(s.pop());
			result.append(s.pop());
		}
		
		return result.toString();
	}	
	
	private static int getPresedence(char ch) {
		return switch(ch) {
		case	'+' -> 1;
		case 	'-' -> 1;
		case 	'*' -> 2;
		case 	'/' -> 2;
		case    '^' -> 3;
		default -> -1;
		};
	}
	
	public static int evaluatePostfix(String expr) {
		Stack<Integer> s = new Stack<>();
		
	}
	
}
