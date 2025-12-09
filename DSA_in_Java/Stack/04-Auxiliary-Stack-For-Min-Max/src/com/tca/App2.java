package com.tca;

import java.util.Random;

import com.tca.util.AdvanceMaxStack;
import com.tca.util.AdvanceMinStack;

public class App2 {

	private static Random random;
	
	public static void main(String[] args) {
		random = new Random();
		AdvanceMinStack<Integer> min = new AdvanceMinStack<>();
		AdvanceMaxStack<Integer> max = new AdvanceMaxStack<>();
		pushRandomElements(min, max, 10);
		
		System.out.println("Min");
		for(int i = 1; i <= 10; ++i) {
			System.out.println("Top : " + min.peek() +  ", Min : " + min.getMinimum());
			min.pop();
		}

		System.out.println("\nMax");
		for(int i = 1; i <= 10; ++i) {
			System.out.println("Top : " + max.peek() +  ", Max : " + max.getMaximum());
			max.pop();
		}
		
		min.clear();
		max.clear();
	}
	
	private static void pushRandomElements(AdvanceMinStack<Integer> min, AdvanceMaxStack<Integer> max, int n) {
		for(int i = 1; i <= n; i++) {
			int element = random.nextInt() % 100;
			
			if(element < 0) {
				i--;
				continue;
			}
			
			min.push(element);
			max.push(element);
		}
	}
}
