package com.tca;

public class Solution {

	public static int isArraySorted(int[] a, int index) {
		
		if(a.length == 1 || index == 1) {
			return 1;
		}
		
		return ( a[index-1] < a[index-2]) ? 0 : isArraySorted(a, index-1);
	}
	
}
