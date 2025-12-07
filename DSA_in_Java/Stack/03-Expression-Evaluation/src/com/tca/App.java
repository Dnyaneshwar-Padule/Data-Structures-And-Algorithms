package com.tca;

import com.tca.util.Stack;

public class App {

	public static void main(String[] args) {
		
		Stack<Integer> s = new Stack<Integer>();
		
		try {
			for(int i = 1; i < 30; ++i)
				s.push(i);
			
			System.out.println("Peek : " + s.peek());
			
			for(int i = 1; i <= 30; ++i) {
				System.out.println("Pop : " + s.pop() + " size : " + s.size());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
