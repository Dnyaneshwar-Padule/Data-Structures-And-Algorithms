package com.tca;

import java.util.Arrays;

public class Solution {

	public static class BinaryStrings{
		private int a[]; 

		public BinaryStrings(int n) {
			a = new int[n];
		}
		

		public void binary(int n) {
			if(n == 0) {
				System.out.println( Arrays.toString(a) );
			}
			else {
				a[a.length - n] = 0;
				binary(n-1);
				a[a.length - n] = 1;
				binary(n-1);
			}
		}

		public void reverseBinary(int n) {
			if(n <= 0) {
				System.out.println( Arrays.toString(a) );
			}
			else {
				a[n-1] = 0;
				reverseBinary(n-1);
				a[n-1] = 1;
				reverseBinary(n-1);
			}
		}

		
	}
}
