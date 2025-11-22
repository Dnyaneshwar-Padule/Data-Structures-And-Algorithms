package com.tca;

public class Solution {

	// O(2^n)
	public static int fibonacci(int n) {
		if (n <= 1)
			return n;
		
		return fibonacci(n-1) + fibonacci(n - 2);
	}
}

/*
	Iterative approach is better than recursive approach, it runs in O(n)
*/