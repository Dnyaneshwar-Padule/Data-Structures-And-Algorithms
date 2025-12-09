package com.tca;

import java.util.Random;

import com.tca.util.MaxStack;
import com.tca.util.MinStack;

public class App {

	private static Random random;
	
	public static void main(String[] args) {
		random = new Random();
		MinStack<Integer> min = new MinStack<>();
		MaxStack<Integer> max = new MaxStack<>();
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
	
	private static void pushRandomElements(MinStack<Integer> min, MaxStack<Integer> max, int n) {
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
