package com.tca;

public class Solution {

	public static void printHello(int n) {
		if(n < 1)
			return;
		
		printHello(n - 1);
		System.out.println("Hello !");
	}
	
}
