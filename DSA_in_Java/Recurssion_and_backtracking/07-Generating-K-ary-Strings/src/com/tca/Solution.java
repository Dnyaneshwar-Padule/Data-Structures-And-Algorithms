package com.tca;

import java.util.Arrays;

public class Solution {

	public static class K_aryStrings{
		private int a[];
		
		public K_aryStrings(int n) {
			a = new int[n];
		}
		
		public void rev_base_K_strings(int n, int k) {
			if(n <= 0) {
				System.out.println(Arrays.toString(a));
				return;
			}
			
			for(int i = 0; i < k; i++) {
				a[n - 1] = i;
				rev_base_K_strings(n-1, k);
			}
		}
		public void base_K_strings(int n, int k) {
			if(n <= 0) {
				System.out.println(Arrays.toString(a));
				return;
			}
			
			for(int i = 0; i < k; i++) {
				a[a.length - n] = i;
				base_K_strings(n-1, k);
			}
		}
	}
}
