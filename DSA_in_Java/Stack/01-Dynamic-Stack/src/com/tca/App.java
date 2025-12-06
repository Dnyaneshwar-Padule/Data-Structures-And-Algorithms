package com.tca;

import com.tca.util.Stack;

public class App {

	public static void main(String[] args) {
		Stack s = new Stack();
		
		try {
			for(int i = 1; i < 30; ++i)
				s.push(i);
			
			System.out.println("Peek : " + s.top());
			
			for(int i = 1; i <= 30; ++i) {
				System.out.println("Pop : " + s.pop());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
}
