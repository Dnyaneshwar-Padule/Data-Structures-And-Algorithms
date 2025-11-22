package com.tca;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {

	public static void main(String[] args) {

		try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
			int n;
			
			System.out.print("Enter n : ");
			n = Integer.parseInt(br.readLine());
			
			for(int i = 0; i < n; i++) {
				System.out.print(Solution.fibonacci(i) + " ");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
