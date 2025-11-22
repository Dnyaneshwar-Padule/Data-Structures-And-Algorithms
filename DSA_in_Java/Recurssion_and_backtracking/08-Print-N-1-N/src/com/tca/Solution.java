package com.tca;

public class Solution {

	public static void print1N1(int n) {
		if(n <= 0) return;
		
		System.out.print(n + " ");
		print1N1( n - 1);
		if(n > 1)System.out.print(n + " ");
		
	}
	
}
