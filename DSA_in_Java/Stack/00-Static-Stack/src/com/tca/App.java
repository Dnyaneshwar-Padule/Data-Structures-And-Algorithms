package com.tca;

import com.tca.util.StaticStack;

public class App {

	public static void main(String[] args) {
		StaticStack stack = new StaticStack();
		
		try {			
			stack.push(10);
			stack.push(20);
			stack.push(30);
			stack.push(40);
			stack.push(50);
			
			System.out.println("Peek : " + stack.peek());
	
			
			System.out.println("Pop : " + stack.pop());
			System.out.println("Pop : " + stack.pop());
			System.out.println("Pop : " + stack.pop());
			System.out.println("Pop : " + stack.pop());
			System.out.println("Pop : " + stack.pop());

			System.out.println("Pop : " + stack.pop());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
