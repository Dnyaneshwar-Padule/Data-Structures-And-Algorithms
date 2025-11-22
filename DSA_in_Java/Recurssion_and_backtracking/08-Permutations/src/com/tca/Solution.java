package com.tca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	private int[] num;
	private boolean[] used;
	private List<List<Integer>> result;
	
	public Solution(int number) {
		int n = Integer.toString(number).length();
		
		num = new int[n];
		used = new boolean[n];
		result = new ArrayList<List<Integer>>();
		
		int i = 0;
		while(number > 0 ) {
			num[ n - i - 1] = number % 10;
			number = number / 10;
			i++;
		}
		
		Arrays.sort(num);
	}
	
	public void permutations(List<Integer> current) {
		if(current.size() == num.length) {
			result.add( new ArrayList<Integer>(current) );
			return;
		}
		
		for(int i = 0; i < num.length; i++) {
	
			if (i > 0 && num[i] == num[i - 1] && !used[i - 1]) continue; 
			if(! used[i] ) {
				used[i] = true;
				current.add(num[i]);
				permutations(current);
				current.remove( current.size() - 1 );
				used[i] = false;
			}
		}
		
	}

	public void displayResult() {

		for(List<Integer> number : result) {
			System.out.println(number);
		}
		
	}
	
	
	
}
