package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

	public static void main(String[] args) {		
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in)))
		{
			int num ;

			System.out.print("Enter a number : ");
			num = Integer.parseInt( br.readLine() );
			
			if( num < 0) {
				System.out.println("Only positive numbers are allowed !");
				return;
			}
			
			int factorial = Solution.factorial(num);
			System.out.println("Factorial of " + num + " is " + factorial);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}

}
