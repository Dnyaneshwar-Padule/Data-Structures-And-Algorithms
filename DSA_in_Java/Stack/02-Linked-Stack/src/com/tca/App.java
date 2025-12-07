package com.tca;

import com.tca.util.LinkedStack;

public class App {

	public static void main(String[] args) {
		LinkedStack<Integer> s = new LinkedStack<Integer>();
		
		try {
			for(int i = 1; i < 30; ++i)
				s.push(i);
			
			System.out.println("Peek : " + s.top());
			
			for(int i = 1; i <= 30; ++i) {
				System.out.println("Pop : " + s.pop() + " size : " + s.size());
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
